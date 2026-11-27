package com.example.distributedsearch

import org.junit.jupiter.api.Assertions._
import org.junit.jupiter.api.Test

class ConsistentHashRouterTest {
  @Test
  def routesDeterministicallyAndDistributesKeysAcrossShards(): Unit = {
    val shardIds = Vector("shard-a", "shard-b", "shard-c")
    val router = new ConsistentHashRouter(shardIds, virtualNodes = 64)
    val assignments = (1 to 6000).map(index => router.route(s"document-$index"))
    val counts = assignments.groupMapReduce(identity)(_ => 1)(_ + _)

    assertEquals(router.route("same-document"), router.route("same-document"))
    assertEquals(3, counts.size)
    assertTrue(counts.values.forall(_ > 1000), s"Unexpectedly uneven distribution: $counts")
    assertTrue(assignments.forall(shardIds.contains))
  }

  @Test
  def rejectsInvalidRingConfigurationAndBlankKeys(): Unit = {
    assertThrows(classOf[IllegalArgumentException], () => new ConsistentHashRouter(Vector.empty))
    val router = new ConsistentHashRouter(Vector("shard-a"))
    assertThrows(classOf[IllegalArgumentException], () => router.route(" "))
  }
}
