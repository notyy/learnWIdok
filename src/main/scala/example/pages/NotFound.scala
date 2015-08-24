package example.pages

import org.scalajs.dom
import org.widok._
import org.widok.bindings.HTML
import example.Routes

case class NotFound() extends Page {
  def view() = HTML.Heading.Level1("Page not found")

  def ready(route: InstantiatedRoute) {
    dom.setTimeout(() => Routes.main().go(), 2000)
  }
}