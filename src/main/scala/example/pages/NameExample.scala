package example.pages

import org.widok._
import org.widok.html._

case class NameExample() extends Page {
  val name = Var("")
  val hasName = name.map(_.nonEmpty)

  def view() = div(
    h1("Welcome to Widok!"),
    p("Please enter your name:"),
    text().bind(name),
    p("Hello, ", name).show(hasName),
    br(),
    button("Change my name")
      .onClick(_ => name := "tux")
      .show(name.isNot("tux")),
    button("Log out")
      .onClick(_ => name := "")
      .show(hasName)
  )

  override def ready(route: InstantiatedRoute): Unit = {}
}