package example.pages

import org.widok._

case class Test() extends Page {
  val query = Channel[String]()

  def view() = Inline("Received parameter: ", query)

  def ready(route: InstantiatedRoute) {
    query := route.args("param")
  }
}
