package example.pages

import org.widok._
import org.widok.html._

case class ABPlusCDProblem() extends Page {
  val a = Var("")
  val b = Var("")
  val c = Var("")
  val d = Var("")

  val numA = a.map(numOrNone)
  val numB = b.map(numOrNone)
  val numC = c.map(numOrNone)
  val numD = d.map(numOrNone)

  val aPlusB = numA + numB
  val cPlusD = numC + numD

  val result = aPlusB + cPlusD

  val strAPlusB = aPlusB.toStrChannel
  val strCPlusD = cPlusD.toStrChannel
  val strResult = result.toStrChannel

  implicit class NumChannelWithPlus(nc: ReadChannel[Option[Double]]){
    def +(anotherNC: ReadChannel[Option[Double]]): ReadChannel[Option[Double]] = numChannelPlus(nc, anotherNC)
    def toStrChannel: ReadChannel[String] = optChannel2Str(nc)

    def numChannelPlus(numCh1:ReadChannel[Option[Double]], numCh2: ReadChannel[Option[Double]]):ReadChannel[Option[Double]] = {
      for {
        optX <- numCh1
        optY <- numCh2
      } yield for{
        x <- optX
        y <- optY
      } yield x + y
    }

    def optChannel2Str(optCh: ReadChannel[Option[Double]]) = optCh.map(_.map(_.toString).getOrElse(""))
  }

  def numOrNone(s: String): Option[Double] = {
    try {
      Some(s.toDouble)
    } catch {
      case _: Throwable => None
    }
  }

  def view() = {
    div(
      label("a ="), text().bind(a), label("b ="), text().bind(b),
      label("c ="), text().bind(c), label("d ="), text().bind(d),
      br(),
      br(),
      label("x = a + b =  ",strAPlusB),
      br(),
      br(),
      label("y = c + d =  ",strCPlusD),
      br(),
      br(),
      label("result = x + y ="), label(strResult)
    )
  }

  def ready(route: InstantiatedRoute) {
    log(s"Page 'main' loaded with route '$route'")
  }

  override def destroy() {
    log("Page 'main' left")
  }
}