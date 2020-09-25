package no.hvl.dat250.rest.todo


data class TodoRequest(
  val summary: String = "",
  val description: String = ""
) {

  fun toTodo(id: Long): Todo {
    return Todo(id, summary, description)
  }
}
