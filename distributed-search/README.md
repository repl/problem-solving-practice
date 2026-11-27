# Distributed Search Interview Demo

This is a small Scala/Spring Boot search cluster in one process. Three logical
shards each have a separate in-memory primary and replica node. The boundaries
are represented as node objects rather than network services so the routing,
fan-out, merge, and failover paths stay easy to inspect and test.

## Run and exercise

Requires Java 17 or newer and Maven:

```shell
mvn test
mvn spring-boot:run
```

The service listens on `http://localhost:8080`.

```shell
curl.exe -X POST http://localhost:8080/api/documents `
  -H "Content-Type: application/json" `
  -d '{"id":"doc-1","title":"Distributed search","body":"Shard indexes and replicas"}'
curl.exe "http://localhost:8080/api/search?q=distributed%20search&limit=10"
curl.exe http://localhost:8080/api/nodes
curl.exe -X POST http://localhost:8080/api/nodes/shard-0-primary/availability `
  -H "Content-Type: application/json" `
  -d '{"available":false}'
```

Restore a node by posting `{"available":true}` to the same availability URL.
`GET /api/nodes` lists the six node IDs. `POST /api/documents` accepts a JSON
document with `id`, `title`, and `body`. `GET /api/search` accepts `q` and an
optional `limit` from 1 to 100. Its response includes merged hits, shards
queried, nodes used, partial failures, cache status, and elapsed time.

## Interview concepts and tradeoffs

| Area | Where to look | What to discuss |
| --- | --- | --- |
| Consistent hashing | `ConsistentHashRouter` | A deterministic ring with virtual nodes routes document IDs to logical shards. It gives stable ownership when membership is unchanged, but this demo does not persist ring membership, move existing data, or implement a production rebalance protocol. |
| Replication and failover | `ShardGroup`, `InMemoryShardNode` | Writes go to the active primary (or replica if the primary is down) and best-effort to its peer. Search prefers the primary and falls back to the replica. This demonstrates availability, not quorum writes, durable replication, split-brain prevention, or automatic replica repair. |
| Indexing and relevance | `InMemoryShardNode` | Documents are held in memory and scored with a simple term-frequency model that weights title matches above body matches. A real engine would add analyzers, inverted indexes, BM25, persistence, and index-version management. |
| Fan-out and backpressure | `SearchCoordinator` | Each query fans out once per logical shard on a bounded worker pool and merges local top results by score. Queue saturation, timeouts, and unavailable replicas become explicit partial failures instead of unbounded queued work. There is no adaptive concurrency or distributed deadline propagation. |
| Staleness and caching | `QueryCache`, `SearchCoordinator` | A bounded LRU caches complete query responses and is cleared by writes and node-availability changes. Failover writes can leave a recovered primary stale; there is no repair process or consistency watermark. |
| Partial failures | `SearchCoordinator` | Failed shards are reported in `partialFailures`; healthy shards still contribute results. This favors useful partial results over failing the whole request, and callers must decide whether that is acceptable for their use case. |
| Monitoring | `RequestLoggingFilter`, search response | Each HTTP request gets an `X-Request-Id` and a log entry with status and duration. Search responses expose elapsed time, nodes used, cache status, and shard failures. Production monitoring would add metrics, traces, alerting, and per-node health checks. |

All data and node state disappear when the process stops. The module is intended
for interview practice, not production deployment.
