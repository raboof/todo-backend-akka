package net.bzzt.todo.backend.akka

import scala.util.Random

case class Todo(id: String = Random.nextInt(Integer.MAX_VALUE).toString, title: String, completed: Boolean = false, order: Int = 0) {
  def uri = s"https://todo-backend-akka.herokuapp.com/todos/$id"
}
case object Todo {
  def apply(title: String, todoUpdate: TodoUpdate): Todo =
    Todo(Todo(title = title), todoUpdate)

  def apply(old: Todo, update: TodoUpdate): Todo =
    Todo(old.id,
          update.title.getOrElse(old.title),
          update.completed.getOrElse(old.completed),
          update.order.getOrElse(old.order))
}
