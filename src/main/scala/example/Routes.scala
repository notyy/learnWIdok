package example

import org.widok._

object Routes {
  val main = Route("/", pages.Main)
  val test = Route("/test/:param", pages.Test)
  val nameExample = Route("/nameExample", pages.NameExample)
  val notFound = Route("/404", pages.NotFound)
  val aPlusBProblem = Route("/aplusb", pages.APlusBProblem)
  val abPlusCDProblem = Route("/abpluscd", pages.ABPlusCDProblem)

  val routes = Set(
    main, test, nameExample,
    aPlusBProblem, abPlusCDProblem,
    notFound)
}

object Main extends RoutingApplication(
  Routes.routes,
  Routes.notFound
)