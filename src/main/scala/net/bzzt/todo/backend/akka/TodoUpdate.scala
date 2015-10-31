package net.bzzt.todo.backend.akka

case class TodoUpdate(title: Option[String] = None, completed: Option[Boolean] = None, order: Option[Int] = None)
