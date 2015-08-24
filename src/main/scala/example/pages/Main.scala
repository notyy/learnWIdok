package example.pages

import example.Routes
import org.widok._
import org.widok.html._

case class Main() extends Page {
  def view() = {
    div(
      a("Link to second page").url(Routes.test("param", "first page")),
      br(),
      a("Link to name change example").url(Routes.nameExample()),
      br(),
      a("link to a plus b problem").url(Routes.aPlusBProblem()),
      br(),
      a("link to ab plus cd problem").url(Routes.abPlusCDProblem())
    )
  }

  def ready(route: InstantiatedRoute) {
    log(s"Page 'main' loaded with route '$route'")
  }

  override def destroy() {
    log("Page 'main' left")
  }
}