# Problem Solving Practice

The original repository contains standalone algorithm exercises. Run examples
with Maven:

```shell
mvn compile exec:java -Dexec.mainClass=stack.ReverseStack
```

## Distributed search interview demo

`distributed-search/` is a separate, runnable Scala and Spring Boot service. It
models a small search cluster in one process using distinct in-memory shard
nodes, so it can be explored without external infrastructure.

Run it with Java 21 and Maven:

```shell
cd distributed-search
mvn test
mvn spring-boot:run
```

The service listens on `http://localhost:8080`:

```shell
curl.exe -X POST http://localhost:8080/api/documents `
  -H "Content-Type: application/json" `
  -d '{"id":"doc-1","title":"Distributed search","body":"Shard indexes and replicas"}'
curl.exe "http://localhost:8080/api/search?q=distributed%20search&limit=10"
curl.exe http://localhost:8080/api/nodes
```

The module README maps the implementation to distributed-search interview
concepts, tradeoffs, and node failure controls.