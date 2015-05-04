package net.bzzt.todo.backend.akka

import spray.json._

import akka.http.scaladsl.marshallers.sprayjson._

trait TodoMarshalling extends SprayJsonSupport
    with FlowMaterializerProvider
    with DefaultJsonProtocol {

  val standardTodoFormat = jsonFormat3(Todo.apply)

  implicit object todoFormat extends RootJsonFormat[Todo] {
    def read(json: JsValue) = standardTodoFormat.read(json)
    def write(todo: Todo) = {
      val fields = standardTodoFormat.write(todo).asJsObject.fields
      JsObject(fields.updated("url", JsString(todo.uri)))
    }
  }


  implicit val todoUpdateFormat = jsonFormat2(TodoUpdate.apply)

}
