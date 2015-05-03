package net.bzzt.todo.backend.akka

import spray.json._

import akka.http.scaladsl.marshallers.sprayjson._

trait TodoMarshalling extends SprayJsonSupport
    with FlowMaterializerProvider
    with DefaultJsonProtocol {
  implicit val todoFormat: RootJsonFormat[Todo] = jsonFormat1(Todo.apply)
}
