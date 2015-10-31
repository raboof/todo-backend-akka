package net.bzzt.todo.backend.akka

import com.typesafe.config._

import akka._
import akka.actor._
import akka.testkit._

import org.scalatest._

class TodoStorageActorTest extends TestKit(
  ActorSystem("TodoStorageActorTest",
      ConfigFactory.parseString("""akka.persistence.journal.leveldb.dir=target/TodoStorageActorTestJournal
        akka.persistence.snapshot-store.leveldb.dir=target/TodoStorageActorTestSnapshots
        """).withFallback(ConfigFactory.load)))
    with WordSpecLike
    with BeforeAndAfterAll
    with ImplicitSender {

  "The Storage-actor" should {
    "keep state across actor restarts" in {
      val initialActor = system.actorOf(Props(new TodoStorageActor))
      initialActor ! TodoStorageActor.Add(TodoUpdate(Some("Todo One"), completed = Some(false), order = Some(1)))
      expectMsgType[Todo]

      initialActor ! TodoStorageActor.Get
      val todos = expectMsgType[Iterable[_]]

      system.stop(initialActor)

      val newActor = system.actorOf(Props(new TodoStorageActor))
      newActor ! TodoStorageActor.Get
      val newTodos = expectMsgType[Iterable[_]]

      assert(newTodos.size == todos.size)
    }
  }

  override def afterAll = {
    system.shutdown()
  }
}
