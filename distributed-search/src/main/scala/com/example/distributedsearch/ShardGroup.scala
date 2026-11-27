package com.example.distributedsearch

final class ShardGroup(val shardId: String, val primary: InMemoryShardNode, val replica: InMemoryShardNode) {
  require(primary.shardId == shardId && replica.shardId == shardId, "Nodes must belong to this shard")

  def nodes: Vector[InMemoryShardNode] = Vector(primary, replica)

  def index(document: Document): Int = {
    val target =
      if (primary.isAvailable) primary
      else if (replica.isAvailable) replica
      else throw new NodeUnavailableException(s"$shardId-primary-and-replica")

    target.index(document)
    val peer = if (target eq primary) replica else primary
    try {
      peer.index(document)
      2
    } catch {
      case _: NodeUnavailableException => 1
    }
  }

  def search(query: String, limit: Int): ShardSearch = {
    try {
      ShardSearch(primary.search(query, limit), primary.nodeId)
    } catch {
      case _: NodeUnavailableException =>
        ShardSearch(replica.search(query, limit), replica.nodeId)
    }
  }
}
