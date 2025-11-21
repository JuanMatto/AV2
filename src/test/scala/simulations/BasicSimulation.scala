package simulations

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class BasicSimulation extends Simulation {

  val httpProtocol = http
    .baseUrl("http://localhost:8080")

  val scn = scenario("Basic Test")
    .exec(
      http("Home Request")
        .get("/")
        .check(status.is(200))
    )

  setUp(
    scn.inject(
      atOnceUsers(10)
    )
  ).protocols(httpProtocol)
}
