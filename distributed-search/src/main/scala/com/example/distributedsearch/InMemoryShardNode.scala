package com.example.distributedsearch

import java.util.Locale
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicBoolean
import scala.jdk.CollectionConverters._

final class InMemoryShardNode(val nodeId: String, val shardId: String) {
  private val documents = new ConcurrentHashMap[String, Document]()
  private val available = new AtomicBoolean(true)

  def isAvailable: Boolean = available.get()

  def setAvailable(value: Boolean): Unit = available.set(value)

  def index(document: Document): Unit = {
    ensureAvailable()
    documents.put(document.id, document)
  }

  def search(query: String, limit: Int): Vector[SearchHit] = {
    ensureAvailable()
    val terms = tokenize(query).distinct
    documents.values().asScala.iterator
      .flatMap { document =>
        val title = tokenize(document.title)
        val body = tokenize(document.body)
        val score = terms.map { term =>
          title.count(_ == term) * 3.0 + body.count(_ == term).toDouble
        }.sum
        if (score > 0.0) Some(SearchHit(document, score, shardId)) else None
      }
      .toVector
      .sortWith { (left, right) =>
        if (left.score == right.score) left.document.id < right.document.id
        else left.score > right.score
      }
      .take(limit)
  }

  def documentCount: Int = documents.size()

  private def ensureAvailable(): Unit =
    if (!available.get()) throw new NodeUnavailableException(nodeId)

  private def tokenize(text: String): Vector[String] =
    Option(text)
      .getOrElse("")
      .toLowerCase(Locale.ROOT)
      .split("[^a-z0-9]+")
      .iterator
      .filter(_.nonEmpty)
      .toVector
}
