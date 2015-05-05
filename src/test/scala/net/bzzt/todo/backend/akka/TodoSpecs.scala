package net.bzzt.todo.backend.akka

import org.scalatest._

import spray.json._

import akka.http.scaladsl.testkit._
import akka.http.scaladsl.server._
import akka.http.scaladsl.model._
import akka.http.scaladsl.model.ContentTypes._

class TodoSpecs extends Suite
    with ScalatestRouteTest
    with RouteTest
    with WordSpecLike
    with ShouldMatchers
    with TodoRoutes
    with TodoStorage {

  "The Todo backend" should {
    "respond to a POST with the todo which was posted to it" in {
      Post("/todos", HttpEntity(`application/json`, """{ "title": "a todo" }""")) ~> routes ~> check {
        status should equal(StatusCodes.OK)
        entityAs[JsObject].fields("title") should equal(JsString("a todo"))
      }
    }

    "create a todo with an order field" in {
      Post("/todos", HttpEntity(`application/json`, """{ "title": "a todo", "order": 523 }""")) ~> routes ~> check {
        status should equal(StatusCodes.OK)
        entityAs[JsObject].fields("order") should equal(JsNumber(523))
      }
    }
  }
}
