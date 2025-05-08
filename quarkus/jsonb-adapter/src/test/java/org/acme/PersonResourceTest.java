package org.acme;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
@TestHTTPEndpoint(PersonResource.class)
class PersonResourceTest {

	@Test
	void shouldUpdatePerson() {
		PersonDTO person = new PersonDTO("John", "Doe", Status.OK);

		given().contentType("application/json").body(person).when().put().then().statusCode(200);
	}

	@Test
	void shouldReturnBadRequest() {
		JsonObject body =
				Json.createObjectBuilder().add("firstName", "Duke").add("lastName", "Java").add("status", "notfound").build();

		given().contentType("application/json").body(body.toString()).when().put().then().statusCode(400);
	}
}