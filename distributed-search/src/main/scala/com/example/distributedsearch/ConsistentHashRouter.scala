package com.example.distributedsearch

import java.nio.charset.StandardCharsets
import java.util.TreeMap

final class ConsistentHashRouter(shardIds: Seq[String], virtualNodes: Int = 64) {
  val logicalShardIds: Set[String] = shardIds.toSet
  require(shardIds.nonEmpty, "At least one shard is required")
  require(shardIds.distinct.size == shardIds.size, "Shard IDs must be unique")
  require(shardIds.forall(_.trim.nonEmpty), "Shard IDs must not be blank")
  require(virtualNodes > 0, "At least one virtual node is required")

  private val ring = new TreeMap[java.lang.Long, String]()

  shardIds.foreach { shardId =>
    (0 until virtualNodes).foreach { replica =>
      ring.put(hash(s"$shardId#$replica"), shardId)
    }
  }

  def route(documentId: String): String = {
    require(documentId != null && documentId.trim.nonEmpty, "Document ID must not be blank")
    val key = hash(documentId)
    val entry = Option(ring.floorEntry(key)).getOrElse(ring.lastEntry())
    entry.getValue
  }

  private def hash(value: String): java.lang.Long = {
    var result = 0xcbf29ce484222325L
    value.getBytes(StandardCharsets.UTF_8).foreach { byte =>
      result ^= byte & 0xffL
      result *= 0x100000001b3L
    }
    java.lang.Long.valueOf(result)
  }
}
