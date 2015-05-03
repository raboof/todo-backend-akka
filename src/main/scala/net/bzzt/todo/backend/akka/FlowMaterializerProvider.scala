package net.bzzt.todo.backend.akka

import akka.stream._

trait FlowMaterializerProvider {
  implicit def mat: FlowMaterializer
}
