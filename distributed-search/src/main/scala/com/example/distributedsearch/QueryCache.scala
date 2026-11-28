package com.example.distributedsearch

import java.util.LinkedHashMap

final class QueryCache(maxEntries: Int) {
  require(maxEntries > 0, "Cache capacity must be positive")

  private val entries = new LinkedHashMap[String, CachedSearch](16, 0.75f, true) {
    override protected def removeEldestEntry(
        eldest: java.util.Map.Entry[String, CachedSearch]
    ): Boolean = size() > maxEntries
  }

  def get(key: String): Option[CachedSearch] = synchronized {
    Option(entries.get(key))
  }

  def put(key: String, value: CachedSearch): Unit = synchronized {
    entries.put(key, value)
  }

  def clear(): Unit = synchronized {
    entries.clear()
  }
}
