package no.hvl.dat250.jpa.basicexample

import javax.persistence.Persistence

private const val PERSISTENCE_UNIT_NAME = "todos"

fun main(args: Array<String>) {
  val factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME)
  val em = factory.createEntityManager()
  // read the existing entries and write to console
  val q = em.createQuery("select t from Todo t")
  val todoList = q.resultList
  for (todo in todoList) {
    println(todo)
  }
  println("Size: " + todoList.size)

  // create new todo
  em.transaction.begin()
  val todo = Todo()
  todo.summary = "This is a test"
  todo.description = "This is a test"
  em.persist(todo)

  em.transaction.commit()

  if (em.getReference(Todo::class.java, todo.id) != null) {
    println("got ${todo.id}")
  }

  em.close()
}

