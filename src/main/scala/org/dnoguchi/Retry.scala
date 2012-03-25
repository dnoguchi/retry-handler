package org.dnoguchi

import scala.util.control.Exception._

object Retry {

  def run[A](f: => A)(count: Int, interval: Int = 3000): Option[A] = {
    allCatch.opt(f) match {
      case result: Some[_] => result
      case None if count >= 1 => Thread.sleep(interval); run(f)(count - 1, interval)
      case None => None
    }
  }

}

