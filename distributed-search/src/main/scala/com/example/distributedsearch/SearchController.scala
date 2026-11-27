package com.example.distributedsearch

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation._

@RestController
@RequestMapping(Array("/api"))
class SearchController(coordinator: SearchCoordinator) {
  @PostMapping(Array("/documents"))
  def ingest(@RequestBody document: Document): IngestResponse =
    coordinator.ingest(document)

  @GetMapping(Array("/search"))
  def search(
      @RequestParam(name = "q") query: String,
      @RequestParam(name = "limit", defaultValue = "10") limit: Int
  ): SearchResponse =
    coordinator.search(query, limit)

  @GetMapping(Array("/nodes"))
  def nodes: Vector[NodeStatus] = coordinator.nodeStatuses

  @PostMapping(Array("/nodes/{nodeId}/availability"))
  def setAvailability(
      @PathVariable("nodeId") nodeId: String,
      @RequestBody request: AvailabilityRequest
  ): NodeStatus =
    coordinator.setNodeAvailability(nodeId, request.available)
}

@RestControllerAdvice
class ApiExceptionHandler {
  @ExceptionHandler(Array(classOf[IllegalArgumentException]))
  def badRequest(exception: IllegalArgumentException): ResponseEntity[ApiError] =
    ResponseEntity.badRequest().body(ApiError(exception.getMessage))

  @ExceptionHandler(Array(classOf[NodeUnavailableException]))
  def unavailable(exception: NodeUnavailableException): ResponseEntity[ApiError] =
    ResponseEntity.status(503).body(ApiError(exception.getMessage))
}
