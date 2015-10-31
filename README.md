todo-backend-akka
=================

[![Build Status](https://travis-ci.org/raboof/todo-backend-akka.svg)](https://travis-ci.org/raboof/todo-backend-akka)

This is an implementation of the [Todo-Backend](http://todobackend.com) API based on [Scala](http://scala-lang.org) and [akka-http](http://doc.akka.io/docs/akka-stream-and-http-experimental/current/scala/http/index.html).

storage
-------

This implementation currently keeps todo's in memory. A backend based on [akka-persistence](http://doc.akka.io/docs/akka/snapshot/scala/persistence.html) and [stamina](https://github.com/scalapenos/stamina) is planned.
