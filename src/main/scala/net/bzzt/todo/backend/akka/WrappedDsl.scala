package net.bzzt.todo.backend.akka

import scala.reflect._

import akka.http.scaladsl.server._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.util.ApplyConverter

object WrappedDsl {
  case class WrappedRoute(inner: Route)
  case class WrappedDirective[T](inner: Directive[T])
  case class WrappedAppliedDirective[T](s: String, d: WrappedDirective[T], inner: T => WrappedRoute) {
    val route = d.inner.tapply(t => inner(t).inner)
  }

  abstract class WrappedApplyConverter[L] {
    type In
    def apply(f: In): L ⇒ WrappedRoute
  }

  implicit def hac1[T1]: WrappedApplyConverter[Tuple1[T1]] { type In = (T1) ⇒ WrappedRoute } = new WrappedApplyConverter[Tuple1[T1]] {
    type In = (T1) ⇒ WrappedRoute
    def apply(fn: In): (Tuple1[T1]) ⇒ WrappedRoute = {
      case Tuple1(t1) ⇒ fn(t1)
    }
  }

  implicit def addWrappedDirectiveApply[L](directive: WrappedDirective[L])(implicit hac: WrappedApplyConverter[L], ct: ClassTag[L]): hac.In ⇒ WrappedAppliedDirective[L] =
    f ⇒ WrappedAppliedDirective[L]("dus " + ct, directive, hac(f))

    implicit def lift[T](d: Directive[T]): WrappedDirective[T] = WrappedDirective(d)
    implicit def lift2[T](d: Route): WrappedRoute = WrappedRoute(d)
  }
