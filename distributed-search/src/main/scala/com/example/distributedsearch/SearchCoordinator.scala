package com.example.distributedsearch

import java.util.concurrent._
import scala.collection.mutable

final class SearchCoordinator(
    val router: ConsistentHashRouter,
    groups: Vector[ShardGroup],
    cache: QueryCache,
    private val executor: ThreadPoolExecutor
) extends AutoCloseable {
  private val groupsById = groups.map(group => group.shardId -> group).toMap
  require(groups.nonEmpty, "At least one shard group is required")
  require(groupsById.size == groups.size, "Shard IDs must be unique")
  require(groupsById.keySet == router.logicalShardIds, "Router and shard groups must have matching IDs")

  def ingest(document: Document): IngestResponse = {
    require(document != null, "Document is required")
    require(document.id != null && document.id.trim.nonEmpty, "Document ID must not be blank")
    require(document.title != null && document.title.trim.nonEmpty, "Document title must not be blank")
    require(document.body != null, "Document body is required")

    val shardId = router.route(document.id)
    val copiesWritten = groupsById(shardId).index(document)
    cache.clear()
    IngestResponse(document.id, shardId, copiesWritten)
  }

  def search(query: String, limit: Int): SearchResponse = {
    require(query != null && query.trim.nonEmpty, "Query must not be blank")
    require(limit >= 1 && limit <= 100, "Limit must be between 1 and 100")

    val startedAt = System.nanoTime()
    val normalizedQuery = query.trim.toLowerCase(java.util.Locale.ROOT).split("\\s+").mkString(" ")
    val cacheKey = s"$normalizedQuery:$limit"

    cache.get(cacheKey) match {
      case Some(cached) =>
        toResponse(cached, cacheHit = true, startedAt)
      case None =>
        val results = mutable.ArrayBuffer.empty[ShardSearch]
        val failures = mutable.ArrayBuffer.empty[PartialFailure]
        val futures = mutable.ArrayBuffer.empty[(ShardGroup, Future[ShardSearch])]

        groups.foreach { group =>
          try {
            val future = executor.submit(new Callable[ShardSearch] {
              override def call(): ShardSearch = group.search(query, limit)
            })
            futures += group -> future
          } catch {
            case _: RejectedExecutionException =>
              failures += PartialFailure(group.shardId, "search queue is full")
          }
        }

        futures.foreach { case (group, future) =>
          try {
            results += future.get(2, TimeUnit.SECONDS)
          } catch {
            case _: TimeoutException =>
              future.cancel(true)
              failures += PartialFailure(group.shardId, "search timed out")
            case _: ExecutionException =>
              failures += PartialFailure(group.shardId, "primary and replica unavailable")
            case _: CancellationException =>
              failures += PartialFailure(group.shardId, "search was cancelled")
            case _: InterruptedException =>
              Thread.currentThread().interrupt()
              failures += PartialFailure(group.shardId, "search was interrupted")
          }
        }

        val merged = results.iterator.flatMap(_.hits).toVector
          .sortWith { (left, right) =>
            if (left.score == right.score) left.document.id < right.document.id
            else left.score > right.score
          }
          .take(limit)
        val cached = CachedSearch(
          merged,
          shardsQueried = groups.size,
          nodesUsed = results.iterator.map(_.nodeId).toVector,
          partialFailures = failures.toVector
        )
        if (failures.isEmpty) cache.put(cacheKey, cached)
        toResponse(cached, cacheHit = false, startedAt)
    }
  }

  def setNodeAvailability(nodeId: String, available: Boolean): NodeStatus = {
    val node = groups.iterator.flatMap(_.nodes).find(_.nodeId == nodeId)
      .getOrElse(throw new IllegalArgumentException(s"Unknown shard node '$nodeId'"))
    node.setAvailable(available)
    cache.clear()
    NodeStatus(node.nodeId, node.shardId, node.isAvailable)
  }

  def nodeStatuses: Vector[NodeStatus] =
    groups.flatMap(_.nodes).map(node => NodeStatus(node.nodeId, node.shardId, node.isAvailable))

  override def close(): Unit = {
    executor.shutdown()
    if (!executor.awaitTermination(2, TimeUnit.SECONDS)) executor.shutdownNow()
  }

  private def toResponse(cached: CachedSearch, cacheHit: Boolean, startedAt: Long): SearchResponse =
    SearchResponse(
      cached.hits,
      cached.shardsQueried,
      cached.nodesUsed,
      cached.partialFailures,
      cacheHit,
      TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startedAt)
    )
}

object SearchCoordinator {
  def localDemo(): SearchCoordinator = {
    val shardIds = Vector("shard-0", "shard-1", "shard-2")
    val groups = shardIds.map { shardId =>
      new ShardGroup(
        shardId,
        new InMemoryShardNode(s"$shardId-primary", shardId),
        new InMemoryShardNode(s"$shardId-replica", shardId)
      )
    }
    val executor = new ThreadPoolExecutor(
      3,
      3,
      0L,
      TimeUnit.MILLISECONDS,
      new ArrayBlockingQueue[Runnable](3),
      new ThreadPoolExecutor.AbortPolicy
    )
    new SearchCoordinator(
      new ConsistentHashRouter(shardIds),
      groups,
      new QueryCache(256),
      executor
    )
  }
}
