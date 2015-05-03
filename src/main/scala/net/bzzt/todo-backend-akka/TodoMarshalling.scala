package net.bzzt.todo.backend.akka

import spray.json._

import akka.http.scaladsl.marshallers.sprayjson._

trait TodoMarshalling extends SprayJsonSupport
    with FlowMaterializerProvider
    with DefaultJsonProtocol {

  val defaultTodoFormat = jsonFormat2(Todo.apply)

  implicit object todoFormat extends RootJsonFormat[Todo] {
    def read(json: JsValue): Todo = {
      val fields = json.asJsObject.fields
      defaultTodoFormat.read(JsObject(fields.updated("completed", fields.get("completed").getOrElse(JsBoolean(false)))))
    }
    def write(todo: Todo): JsValue = defaultTodoFormat.write(todo)
  }
}
