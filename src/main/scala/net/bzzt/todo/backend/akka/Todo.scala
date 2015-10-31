package net.bzzt.todo.backend.akka

import scala.util.Random

case class Todo(id: String, title: String, completed: Boolean = false, order: Int = 0)
case object Todo {
  private def nextId() =  Random.nextInt(Integer.MAX_VALUE).toString

  def apply(title: String, todoUpdate: TodoUpdate): Todo = {
    Todo(Todo(nextId(), title), todoUpdate)
  }

  def apply(old: Todo, update: TodoUpdate): Todo =
    Todo(old.id,
          update.title.getOrElse(old.title),
          update.completed.getOrElse(old.completed),
          update.order.getOrElse(old.order))
}
