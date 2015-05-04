package net.bzzt.todo.backend.akka

import scala.util.Random

case class Todo(id: Int = Random.nextInt(Integer.MAX_VALUE), title: String, completed: Boolean) {
  def uri = s"https://todo-backend-akka.herokuapp.com/todos/$id"
}
case object Todo {
  def apply(title: String, todoUpdate: TodoUpdate): Todo =
    Todo(title = title,
      completed = todoUpdate.completed.getOrElse(false))

}
