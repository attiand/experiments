package test;

import java.net.URI;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RestClientTest {

	@Test
	void testLogFilter() {

		// sim-ladok
		URI uri = UriBuilder.fromUri("http://nya-01.nya-srv.its.umu.se:61080").path("api/applikation/version").build();

		try(Client client = ClientBuilder.newBuilder().property("dev.resteasy.client.follow.redirects", "true").register(ClientResponseLogFilter.class).build()) {
			try (Response response = client.target(uri).request(MediaType.APPLICATION_JSON).get()) {
				var status = response.getStatusInfo();
				assertThat(status.getFamily()).isEqualTo(Response.Status.Family.SUCCESSFUL);
			}
		}
	}

	@Test
	void testCookieHander() {
		Client client = ((ResteasyClientBuilder) ClientBuilder.newBuilder()).enableCookieManagement().setFollowRedirects(true).build();
	}
}
