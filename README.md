todo-backend-akka
=================

[![Build Status](https://travis-ci.org/raboof/todo-backend-akka.svg)](https://travis-ci.org/raboof/todo-backend-akka)

This is an implementation of the [Todo-Backend](http://todobackend.com) API based on [Scala](http://scala-lang.org) and [akka-http](http://doc.akka.io/docs/akka/current/scala/http/index.html).

storage
-------

* The master branch keeps todo's in memory. 
* The stamina-serialization branch persists todo's with [akka-persistence](http://doc.akka.io/docs/akka/snapshot/scala/persistence.html) and [stamina](https://github.com/scalapenos/stamina)
* The stamina-eventadapter branch persists todo's with [akka-persistence](http://doc.akka.io/docs/akka/snapshot/scala/persistence.html) and [stamina](https://github.com/scalapenos/stamina)'s experimental `EventAdapter`-based hook.
