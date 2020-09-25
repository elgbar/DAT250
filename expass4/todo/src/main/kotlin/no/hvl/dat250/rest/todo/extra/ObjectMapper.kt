package no.hvl.dat250.rest.todo.extra

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue

inline fun <reified T> ObjectMapper.readValueOrNull(content: String): T? {
  return try {
    readValue<T>(content)
  } catch (e: Exception) {
    null
  }
}
