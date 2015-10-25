package net.bzzt.todo.backend.akka

import spray.json._
import DefaultJsonProtocol._

import stamina.json._

object TodoStorageAkkaSerialization {

  implicit val todoFormat = jsonFormat4(Todo.apply)
  implicit val addedFormat = jsonFormat1(TodoStorageActor.Added)
  implicit val todoUpdateFormat = jsonFormat3(TodoUpdate)
  implicit val updatedFormat = jsonFormat2(TodoStorageActor.Updated)

  val persisters = List(
    persister[TodoStorageActor.Added]("TodoStorageActor.Added"),
    persister[TodoStorageActor.Updated]("TodoStorageActor.Updated")
  )
}
