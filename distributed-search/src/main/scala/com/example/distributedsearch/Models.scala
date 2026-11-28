package com.example.distributedsearch

final case class Document(id: String, title: String, body: String)

final case class SearchHit(document: Document, score: Double, shardId: String)

final case class ShardSearch(hits: Vector[SearchHit], nodeId: String)

final case class PartialFailure(shardId: String, reason: String)

final case class SearchResponse(
    hits: Vector[SearchHit],
    shardsQueried: Int,
    nodesUsed: Vector[String],
    partialFailures: Vector[PartialFailure],
    cacheHit: Boolean,
    tookMs: Long
)

final case class IngestResponse(id: String, shardId: String, copiesWritten: Int)

final case class AvailabilityRequest(available: Boolean)

final case class NodeStatus(nodeId: String, shardId: String, available: Boolean)

final case class ApiError(error: String)

final case class CachedSearch(
    hits: Vector[SearchHit],
    shardsQueried: Int,
    nodesUsed: Vector[String],
    partialFailures: Vector[PartialFailure]
)

final class NodeUnavailableException(val nodeId: String)
    extends RuntimeException(s"Shard node '$nodeId' is unavailable")
