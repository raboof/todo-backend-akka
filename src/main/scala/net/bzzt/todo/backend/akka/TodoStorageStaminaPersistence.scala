package net.bzzt.todo.backend.akka

import spray.json._
import DefaultJsonProtocol._

import stamina.json._

object TodoStorageStaminaPersistence {
  import fommil.sjs.FamilyFormats._

  val persisters = List(
    persister[TodoStorageActor.Added]("TodoStorageActor.Added"),
    persister[TodoStorageActor.Updated]("TodoStorageActor.Updated"),
    persister[TodoStorageActor.Deleted]("TodoStorageActor.Deleted"),
    persister[TodoStorageActor.Cleared.type]("TodoStorageActor.Cleared")
  )
}
