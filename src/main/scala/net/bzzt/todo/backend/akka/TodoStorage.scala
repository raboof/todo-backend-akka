package net.bzzt.todo.backend.akka

import akka.actor._
import akka.persistence._

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

  sealed trait Event extends stamina.Persistable
  case class Added(todo: Todo) extends Event
  case class Updated(id: String, update: TodoUpdate) extends Event
  case class Deleted(id: String) extends Event
  case object Cleared extends Event
}
class TodoStorageActor extends PersistentActor {
  import TodoStorageActor._

  override val persistenceId = "todos"

  var todos: Map[String, Todo] = Map()

  def receiveCommand = {
    case Get =>
      sender() ! todos.values
    case Get(id) =>
      sender() ! todos.get(id).getOrElse(Status.Failure(new IllegalStateException("ID not found")))
    case Add(todoUpdate) =>
      require(todoUpdate.title.isDefined, "Title is required")
      val todo = Todo(todoUpdate.title.get, todoUpdate)

      persist(Added(todo)) { evt =>
        updateState(evt)
        sender() ! todo
      }
    case Update(id, update) =>
      persist(Updated(id, update)) { evt =>
        updateState(evt)
        self.forward(Get(id))
      }
    case Delete(id) =>
      persist(Deleted(id)) { evt =>
        updateState(evt)
        sender() ! Status.Success()
      }
    case Clear =>
      persist(Cleared) { evt =>
        updateState(evt)
        sender() ! Status.Success()
      }
  }

  def receiveRecover = {
    case evt: Event => updateState(evt)
  }

  def updateState(event: Event) = event match {
    case Added(todo) =>
      todos = todos.updated(todo.id.toString, todo)
    case Updated(id: String, update: TodoUpdate) =>
      todos = todos.mapValue(id, old => Todo(old, update))
    case Deleted(id: String) =>
      todos = todos.filter(_._1 != id)
    case Cleared =>
      todos = Map()
  }
}
