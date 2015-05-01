package net.bzzt.todo.backend.akka

import akka.http.scaladsl.model._
import akka.http.scaladsl.model.HttpMethods._
import akka.http.scaladsl.model.headers._

import akka.http.scaladsl.server._
import akka.http.scaladsl.server.Directives._

trait TodoRoutes {
  def routes = {
    respondWithHeaders(
      `Access-Control-Allow-Origin`.`*`,
      `Access-Control-Allow-Headers`("Accept", "Content-Type"),
      `Access-Control-Allow-Methods`(GET, HEAD, POST, DELETE, OPTIONS, PUT, PATCH)
    ) {
      get {
        complete(StatusCodes.OK)
      } ~
      options {
        complete(StatusCodes.OK)
      }
    }
  }
}
