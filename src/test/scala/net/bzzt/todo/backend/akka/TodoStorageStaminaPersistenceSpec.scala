package net.bzzt.todo.backend.akka

import spray.json._
import stamina._
import stamina.testkit._
import stamina.json._

import org.scalatest._

class TodoStorageStaminaPersistenceSpec extends Suite
  with WordSpecLike
  with StaminaTestKit {

    val persisters = Persisters(TodoStorageStaminaPersistence.persisters.map(toByteArrayPersister(_)))

    "The TodoStorageActor persisters" should {
      val id = "42"
      persisters.generateTestsFor(
        sample(TodoStorageActor.Added(Todo(id, "foo"))),
        sample(TodoStorageActor.Updated(id, TodoUpdate(completed = Some(true)))),
        sample(TodoStorageActor.Deleted(id)),
        sample(TodoStorageActor.Cleared)
      )
    }
}
