package test;

import java.net.URI;

import jakarta.json.JsonObject;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class JsonbTest {

	@Test
	void testLogFilter() {

		// sim-ladok
		URI uri = UriBuilder.fromUri("http://nya-01.nya-srv.its.umu.se:61080").path("api/applikation/version").build();

		try(Client client = ClientBuilder.newBuilder().register(ClientResponseLogFilter.class).build()) {
			try (Response response = client.target(uri).request(MediaType.APPLICATION_JSON).get()) {
				var status = response.getStatusInfo();

				assertThat(status.getFamily()).isEqualTo(Response.Status.Family.SUCCESSFUL);

				JsonObject version = response.readEntity(JsonObject.class);

				assertThat(version.getString("scm")).isEqualTo("7ef663510c9208e1ad8030cc3a6568f47d0213f3");
				assertThat(version.getJsonObject("build").getString("number")).isEqualTo("311");
			}
		}
	}
}
