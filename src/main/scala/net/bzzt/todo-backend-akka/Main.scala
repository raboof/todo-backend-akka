package net.bzzt.todo.backend.akka

import scala.concurrent._

import akka.actor._

import akka.stream._
import akka.stream.scaladsl._

import akka.http.scaladsl.Http

object Main {
  def main(args: Array[String]) {
    implicit val system = ActorSystem()
    implicit val materializer = ActorFlowMaterializer()

    val serverSource: Source[Http.IncomingConnection, Future[Http.ServerBinding]] =
      Http(system).bind(interface = "localhost", port = 8080)


    val bindingFuture: Future[Http.ServerBinding] = serverSource.to(Sink.foreach { connection =>
      // foreach materializes the source
      println("Accepted new connection from " + connection.remoteAddress)
      // ... and then actually handle the connection
    }).run()
  }
}
