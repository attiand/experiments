package test;

import java.net.URI;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.JsonNode;

import static org.assertj.core.api.Assertions.assertThat;

class RestClientTest {

	@Test
	void testJsonNode() {

		// sim-ladok
		URI uri = UriBuilder.fromUri("http://nya-01.nya-srv.its.umu.se:61080").path("api/applikation/version").build();

		try(Client client = ClientBuilder.newBuilder().build()) {
			try (Response response = client.target(uri).request(MediaType.APPLICATION_JSON).get()) {
				var status = response.getStatusInfo();
				assertThat(status.getFamily()).isEqualTo(Response.Status.Family.SUCCESSFUL);
				JsonNode jsonNode = response.readEntity(JsonNode.class);
				System.out.println(jsonNode);
				System.out.println(jsonNode.get("scm"));
			}
		}
	}
}
