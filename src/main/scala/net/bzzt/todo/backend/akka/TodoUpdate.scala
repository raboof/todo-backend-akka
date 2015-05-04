package net.bzzt.todo.backend.akka

case class TodoUpdate(title: Option[String], completed: Option[Boolean])
