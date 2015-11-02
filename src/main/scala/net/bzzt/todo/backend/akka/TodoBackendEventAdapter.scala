package net.bzzt.todo.backend.akka

import spray.json._
import stamina.eventadapters._

class TodoBackendEventAdapter extends StaminaEventAdapter[JsValue](TodoStorageStaminaPersistence.persisters)
