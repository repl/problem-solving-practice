package com.example.distributedsearch

import java.util.concurrent.{ArrayBlockingQueue, ThreadPoolExecutor, TimeUnit}
import org.junit.jupiter.api.Assertions._
import org.junit.jupiter.api.Test

class SearchCoordinatorTest {
  @Test
  def fansOutToEveryShardAndMergesByRelevance(): Unit = withCoordinator(Vector("a", "b")) {
    (coordinator, groups) =>
      groups(0).index(Document("strong", "Distributed search", ""))
      groups(1).index(Document("weaker", "Search", "distributed"))

      val result = coordinator.search("distributed search", 10)

      assertEquals(2, result.shardsQueried)
      assertEquals(Vector("strong", "weaker"), result.hits.map(_.document.id))
      assertEquals(2, result.nodesUsed.size)
      assertTrue(result.partialFailures.isEmpty)
  }

  @Test
  def routesWritesAndFailsOverFromPrimaryToReplica(): Unit = withCoordinator(Vector("a", "b")) {
    (coordinator, groups) =>
      val document = Document("doc-1", "Replica search", "available")
      val ingested = coordinator.ingest(document)
      val group = groups.find(_.shardId == ingested.shardId).get
      assertEquals(2, ingested.copiesWritten)
      assertEquals(1, group.primary.documentCount)
      assertEquals(1, group.replica.documentCount)

      coordinator.setNodeAvailability(group.primary.nodeId, available = false)
      val result = coordinator.search("replica", 10)

      assertEquals(Vector(document.id), result.hits.map(_.document.id))
      assertTrue(result.nodesUsed.contains(group.replica.nodeId))
  }

  @Test
  def cachesCompleteQueriesAndInvalidatesAfterAnIngest(): Unit = withCoordinator(Vector("a", "b")) {
    (coordinator, _) =>
      coordinator.ingest(Document("first", "Search basics", ""))
      assertFalse(coordinator.search("search", 10).cacheHit)
      assertTrue(coordinator.search("search", 10).cacheHit)

      coordinator.ingest(Document("second", "Search at scale", ""))
      val refreshed = coordinator.search("search", 10)

      assertFalse(refreshed.cacheHit)
      assertEquals(2, refreshed.hits.size)
  }

  @Test
  def returnsHealthyShardResultsAlongsideAnExplicitPartialFailure(): Unit = withCoordinator(Vector("a", "b")) {
    (coordinator, groups) =>
      groups(0).index(Document("healthy", "Distributed systems", ""))
      groups(1).primary.setAvailable(false)
      groups(1).replica.setAvailable(false)

      val result = coordinator.search("distributed", 10)

      assertEquals(Vector("healthy"), result.hits.map(_.document.id))
      assertEquals(1, result.partialFailures.size)
      assertEquals(2, result.shardsQueried)
      assertFalse(result.cacheHit)
  }

  private def withCoordinator(shardIds: Vector[String])(
      test: (SearchCoordinator, Vector[ShardGroup]) => Unit
  ): Unit = {
    val groups = shardIds.map { shardId =>
      new ShardGroup(
        shardId,
        new InMemoryShardNode(s"$shardId-primary", shardId),
        new InMemoryShardNode(s"$shardId-replica", shardId)
      )
    }
    val executor = new ThreadPoolExecutor(
      shardIds.size,
      shardIds.size,
      0L,
      TimeUnit.MILLISECONDS,
      new ArrayBlockingQueue[Runnable](shardIds.size)
    )
    val coordinator = new SearchCoordinator(
      new ConsistentHashRouter(shardIds),
      groups,
      new QueryCache(16),
      executor
    )
    try test(coordinator, groups)
    finally coordinator.close()
  }
}
