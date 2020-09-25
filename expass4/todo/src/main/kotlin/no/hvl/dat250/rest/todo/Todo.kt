package no.hvl.dat250.rest.todo

import java.util.concurrent.atomic.AtomicLong


data class Todo(val id: Long, val summary: String, val description: String) {

  companion object {
    private val idCounter = AtomicLong(0)
    val nextId get() = idCounter.incrementAndGet()
  }
}
