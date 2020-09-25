package no.hvl.dat250.rest.todo

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import no.hvl.dat250.rest.todo.Todo.Companion.nextId
import no.hvl.dat250.rest.todo.extra.readValueOrNull
import spark.Request
import spark.Response
import spark.Spark
import spark.Spark.after
import spark.Spark.delete
import spark.Spark.get
import spark.Spark.post
import spark.Spark.put
import java.util.concurrent.ConcurrentHashMap

/**
 * Hello world!
 */


private val todoMap = ConcurrentHashMap<Long, Todo>()
private const val TODO_PATH = "/todo"
private val mapper = jacksonObjectMapper()

const val INVALID_BODY = "Failed to parse body"
const val INVALID_ID = "No TODO can be found with given id"
const val INVALID_RESPONSE = "Failed to generate invalid response"

const val ID_QUERY_NAME = "id"
  
fun main(args: Array<String>) {
  Spark.port(if (args.isNotEmpty()) args[0].toIntOrNull() ?: 8080 else 8080)

  after({ _, res -> res.type("application/json") })

  post(TODO_PATH) { req: Request, res: Response ->
    res.status(400)
    val requestTodo = mapper.readValueOrNull<TodoRequest>(req.body()) ?: return@post INVALID_BODY
    val actualTodo = requestTodo.toTodo(nextId)

    todoMap.putIfAbsent(actualTodo.id, actualTodo)

    mapper.writeValueAsString(actualTodo)?.also {
      res.status(200)
    } ?: INVALID_RESPONSE
  }

  get(TODO_PATH) { req: Request, res: Response ->
    res.status(400)
    val id = req.queryParams(ID_QUERY_NAME).toLongOrNull() ?: return@get INVALID_ID

    res.status(404)
    val todo = todoMap[id] ?: return@get INVALID_ID

    mapper.writeValueAsString(todo)?.also {
      res.status(200)
    } ?: INVALID_RESPONSE
  }

  put(TODO_PATH) { req: Request, res: Response ->

    res.status(404)
    val id = req.queryParams(ID_QUERY_NAME).toLongOrNull() ?: return@put INVALID_ID

    res.status(400)
    val (summary, description) = mapper.readValueOrNull<TodoRequest>(req.body()) ?: return@put INVALID_BODY

    res.status(404)
    val newTodo = todoMap.computeIfPresent(id) { _: Long, _: Todo ->
      Todo(id, summary, description)
    } ?: return@put INVALID_ID

    mapper.writeValueAsString(newTodo)?.also {
      res.status(200)
    } ?: INVALID_RESPONSE
  }

  delete(TODO_PATH) { req: Request, res: Response ->
    res.status(404)
    val id = req.queryParams(ID_QUERY_NAME).toLongOrNull() ?: return@delete INVALID_ID

    val old = todoMap.remove(id) ?: return@delete INVALID_ID
    mapper.writeValueAsString(old)?.also {
      res.status(200)
    } ?: INVALID_RESPONSE
  }
}
