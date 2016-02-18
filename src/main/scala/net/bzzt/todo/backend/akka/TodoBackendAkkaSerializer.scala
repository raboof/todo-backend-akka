package net.bzzt.todo.backend.akka

import stamina._

class TodoBackendAkkaSerializer
  extends StaminaAkkaSerializer(TodoStorageAkkaSerialization.persisters)
