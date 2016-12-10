package net.bzzt.todo.backend.akka

import akka.actor._

import MapUtils._

trait TodoStorage {
  implicit val system: ActorSystem

  lazy val todoStorage: ActorRef = system.actorOf(Props(new TodoStorageActor))
}

object TodoStorageActor {
  sealed trait Command
  case object Get extends Command
  case class Get(id: String) extends Command
  case class Add(todo: TodoUpdate) extends Command
  case class Update(id: String, todo: TodoUpdate) extends Command
  case class Delete(id: String) extends Command
  case object Clear extends Command
}
class TodoStorageActor extends Actor {
  import TodoStorageActor._

  var todos: Map[String, Todo] = Map()

  def receive = {
    case Get =>
      sender() ! todos.values
    case Get(id) =>
      sender() ! todos.get(id).getOrElse(Status.Failure(new IllegalStateException("ID not found")))
    case Add(todoUpdate) =>
      val todo = todoUpdate.title.map(Todo(_, todoUpdate))
      todos = todos ++ todo.map(todo => todo.id.toString -> todo)
      sender() ! todo.getOrElse(Status.Failure(new IllegalArgumentException("Insufficient data")))
    case Update(id, update) =>
      todos = todos.mapValue(id, old => Todo(old, update))
      self.forward(Get(id))
    case Delete(id) =>
      todos = todos.filter(_._1 != id)
      sender() ! Status.Success(())
    case Clear =>
      todos = Map()
      sender() ! Status.Success(())
  }
}
