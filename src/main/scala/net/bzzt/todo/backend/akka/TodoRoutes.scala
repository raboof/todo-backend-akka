package net.bzzt.todo.backend.akka

import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

import akka.http.scaladsl.model._
import akka.http.scaladsl.model.HttpMethods._
import akka.http.scaladsl.model.headers._

import akka.http.scaladsl.server._
import akka.http.scaladsl.server.Directives._

import akka.http.scaladsl.unmarshalling._
import akka.http.scaladsl.marshalling._

import akka.actor._
import akka.pattern._
import akka.util._

trait TodoRoutes extends TodoMarshalling
    with TodoStorageProvider {

  implicit val timeout: Timeout = 2 seconds

  def routes = {
    respondWithHeaders(
      `Access-Control-Allow-Origin`.`*`,
      `Access-Control-Allow-Headers`("Accept", "Content-Type"),
      `Access-Control-Allow-Methods`(GET, HEAD, POST, DELETE, OPTIONS, PUT, PATCH)
    ) {
      get {
        onSuccess(todoStorage ? TodoStorageActor.Get) { todos =>
          complete(StatusCodes.OK, todos.asInstanceOf[Seq[Todo]])
        }
      } ~
      post {
        entity(as[TodoUpdate]) { update =>
          onSuccess(todoStorage ? TodoStorageActor.Add(update)) { todo =>
            complete(StatusCodes.OK, todo.asInstanceOf[Todo])
          }
        }
      } ~
      delete {
        onSuccess(todoStorage ? TodoStorageActor.Clear) { _ =>
          complete(StatusCodes.OK)
        }
      } ~
      options {
        complete(StatusCodes.OK)
      }
    }
  }
}
