package net.bzzt.todo.backend.akka

import akka.actor._

trait TodoStorage {
  implicit val system: ActorSystem

  lazy val todoStorage: ActorRef = system.actorOf(Props(new TodoStorageActor)) 
}

object TodoStorageActor {
  sealed trait Command
  case object Get extends Command
  case class Add(todo: Todo) extends Command
  case object Clear extends Command
}
class TodoStorageActor extends Actor {
  import TodoStorageActor._

  var todos: Seq[Todo] = List()

  def receive = {
    case Get =>
      sender() ! todos
    case Add(todo) =>
      todos = todos :+ todo
      sender() ! Status.Success()
    case Clear =>
      todos = List()
      sender() ! Status.Success()
  }
}
