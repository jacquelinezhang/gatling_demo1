import io.gatling.core.Predef._
import io.gatling.http.Predef._

class RecordedSimulation extends Simulation {

	val httpProtocol = http
		.baseURL("http://computer-database.gatling.io")
		.inferHtmlResources()
		.acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
		.acceptEncodingHeader("gzip, deflate, sdch")
		.acceptLanguageHeader("en-US,en;q=0.8")
		.userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36")

	val headers_0 = Map("Upgrade-Insecure-Requests" -> "1")

	val headers_6 = Map(
		"Accept-Encoding" -> "gzip, deflate",
		"Origin" -> "http://computer-database.gatling.io",
		"Upgrade-Insecure-Requests" -> "1")

	val scn = scenario("RecordedSimulation")
		// Search
		.exec(http("request_0")
			.get("/")
			.headers(headers_0))
		.pause(11)
		.exec(http("request_1")
			.get("/computers?f=macbook")
			.headers(headers_0))
		.pause(1)
		.exec(http("request_2")
			.get("/computers?f=macbook")
			.headers(headers_0))
		.pause(14)
		// browse
		.exec(http("request_3")
			.get("/computers/517")
			.headers(headers_0))
		.pause(5)
		.exec(http("request_4")
			.get("/computers")
			.headers(headers_0))
		.pause(10)
		// Add
		.exec(http("request_5")
			.get("/computers/new")
			.headers(headers_0))
		.pause(7)
		.exec(http("request_6")
			.post("/computers")
			.headers(headers_6)
			.formParam("name", "macbook-pro")
			.formParam("introduced", "")
			.formParam("discontinued", "")
			.formParam("company", ""))

	setUp(scn.inject(atOnceUsers(10))).protocols(httpProtocol)

}