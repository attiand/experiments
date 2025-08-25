package test;

import java.net.URI;

import jakarta.json.JsonObject;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EmptyPostTest {

	@Test
	void testLogFilter() {

		// sim-ladok
		URI uri = UriBuilder.fromUri("http://nya-01.nya-srv.its.umu.se:61080").path("api/applikation/version").build();

		try(Client client = ClientBuilder.newBuilder().register(ClientResponseLogFilter.class).build()) {
			try (Response response = client.target(uri).request().post(Entity.text(""))) {
				var status = response.getStatusInfo();
				assertThat(status.getFamily()).isEqualTo(Response.Status.Family.SUCCESSFUL);
			}
		}
	}
}
