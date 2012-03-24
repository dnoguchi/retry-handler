package org.dnoguchi

object Retry {

  def run[A](f: => A)(count: Int, interval: Int = 3000): Option[A] = {
    exec(f) match {
      case result: Some[_] => result
      case None if count >= 1 => Thread.sleep(interval); run(f)(count - 1, interval)
      case None => None
    }
  }

  private def exec[A](f: => A): Option[A] = {
    try {
      Some(f)
    } catch {
      case _ => None
    }
  }

}

