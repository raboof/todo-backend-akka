package net.bzzt.todo.backend.akka

import stamina._

class TodoBackendAkkaSerializer(persisters: List[Persister[_, _]]) extends StaminaAkkaSerializer(persisters) {
  def this() = this(TodoStorageAkkaSerialization.persisters)
}
