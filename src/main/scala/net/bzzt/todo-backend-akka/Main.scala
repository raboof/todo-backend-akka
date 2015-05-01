package net.bzzt.todo.backend.akka

import scala.concurrent._
import scala.util._

import akka.actor._

import akka.stream._
import akka.stream.scaladsl._

import akka.http.scaladsl.Http

object Main {
  def main(args: Array[String]) {
    val port = Properties.envOrElse("PORT", "8080").toInt
    implicit val system = ActorSystem()
    implicit val materializer = ActorFlowMaterializer()

    val serverSource: Source[Http.IncomingConnection, Future[Http.ServerBinding]] =
      Http(system).bind(interface = "localhost", port = port)


    val bindingFuture: Future[Http.ServerBinding] = serverSource.to(Sink.foreach { connection =>
      // foreach materializes the source
      println("Accepted new connection from " + connection.remoteAddress)
      // ... and then actually handle the connection
    }).run()
  }
}
