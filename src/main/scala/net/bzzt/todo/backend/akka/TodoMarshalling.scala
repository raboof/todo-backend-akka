package net.bzzt.todo.backend.akka

import spray.json._

import akka.http.scaladsl.marshallers.sprayjson._

trait TodoMarshalling extends SprayJsonSupport
    with FlowMaterializerProvider
    with DefaultJsonProtocol {

  val standardTodoFormat = jsonFormat4(Todo.apply)

  def todoFormatFor(baseUrl: String) = new RootJsonFormat[Todo] {
    def read(json: JsValue) = standardTodoFormat.read(json)
    def write(todo: Todo) = {
      val fields = standardTodoFormat.write(todo).asJsObject.fields
      JsObject(fields.updated("url", JsString(baseUrl + '/' + todo.id)))
    }
  }

  implicit val todoUpdateFormat = jsonFormat3(TodoUpdate.apply)
}
