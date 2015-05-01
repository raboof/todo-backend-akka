package net.bzzt.todo.backend.akka

import akka.http.scaladsl.model._

import akka.http.scaladsl.server._
import akka.http.scaladsl.server.Directives._

trait TodoRoutes {
  def routes = {
    get {
      complete(StatusCodes.OK)
    }   
  }
}
