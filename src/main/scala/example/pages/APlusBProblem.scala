package example.pages

import org.widok._
import org.widok.html._

case class APlusBProblem() extends Page {
  val a = Var("")
  val b = Var("")
  val numA: ReadChannel[Option[Double]] = a.map(numOrNone)
  val numB: ReadChannel[Option[Double]] = b.map(numOrNone)

  val aPlusB: ReadChannel[Option[Double]] = for {
    optX <- numA
    optY <- numB
  } yield for{
      x <- optX
      y <- optY
    } yield x + y

  val strAPlusB: ReadChannel[String] = aPlusB.map(_.map(_.toString).getOrElse(""))

  def numOrNone(s: String): Option[Double] = {
    try {
      Some(s.toDouble)
    } catch {
      case _: Throwable => None
    }
  }

  def view() = {
    div(
      label("a="), text().bind(a), label(","), label("b="), text().bind(b),
      br(),
      br(),
      label("a + b =  "), label(strAPlusB)
    )
  }

  def ready(route: InstantiatedRoute) {
    log(s"Page 'main' loaded with route '$route'")
  }

  override def destroy() {
    log("Page 'main' left")
  }
}