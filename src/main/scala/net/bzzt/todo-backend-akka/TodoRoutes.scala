package net.bzzt.todo.backend.akka

import scala.concurrent.ExecutionContext.Implicits.global

import akka.http.scaladsl.model._
import akka.http.scaladsl.model.HttpMethods._
import akka.http.scaladsl.model.headers._

import akka.http.scaladsl.server._
import akka.http.scaladsl.server.Directives._

import akka.http.scaladsl.unmarshalling._
import akka.http.scaladsl.marshalling._

trait TodoRoutes
    extends TodoMarshalling {

  def routes = {
    respondWithHeaders(
      `Access-Control-Allow-Origin`.`*`,
      `Access-Control-Allow-Headers`("Accept", "Content-Type"),
      `Access-Control-Allow-Methods`(GET, HEAD, POST, DELETE, OPTIONS, PUT, PATCH)
    ) {
      post {
        entity(as[Todo]) { todo =>
          complete(StatusCodes.OK, todo)
        }
      } ~
      get {
        complete(StatusCodes.OK)
      } ~
      options {
        complete(StatusCodes.OK)
      }
    }
  }
}
