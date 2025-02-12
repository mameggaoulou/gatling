package computerdatabase;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;
import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

public class RsTest1 extends Simulation {

  private HttpProtocolBuilder httpProtocol = http
    .baseUrl("https://computer-database.gatling.io")
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
    .acceptEncodingHeader("gzip, deflate, br")
    .acceptLanguageHeader("fr-FR,fr;q=0.9,en-US;q=0.8,en;q=0.7")
    .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) Chrome/132.0.0.0");

  private ScenarioBuilder scn = scenario("RsTest1")
    .exec(
      http("Get Computers List")
        .get("/computers"),
      http("Get Computer Details")
        .get("/computers/381"),
      http("Delete Computer")
        .post("/computers/381/delete"),
      http("New Computer Page")
        .get("/computers/new"),
      http("Create Computer")
        .post("/computers")
        .formParam("name", "Ace2")
        .formParam("introduced", "")
        .formParam("discontinued", "")
        .formParam("company", ""),
      http("Search Computer")
        .get("/computers?f=ace")
    );

  {
    setUp(scn.injectOpen(atOnceUsers(5))).protocols(httpProtocol);
  }
}
