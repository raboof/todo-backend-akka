package net.bzzt.todo.backend.akka

import akka.actor._

trait TodoStorageProvider {
  val todoStorage: ActorRef
}
