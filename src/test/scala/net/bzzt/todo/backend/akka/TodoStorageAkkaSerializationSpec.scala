package net.bzzt.todo.backend.akka

import stamina._
import stamina.testkit._

import org.scalatest._

class TodoStorageAkkaSerializationSpec extends Suite
  with WordSpecLike
  with StaminaTestKit {

    val persisters = Persisters(TodoStorageAkkaSerialization.persisters)

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
