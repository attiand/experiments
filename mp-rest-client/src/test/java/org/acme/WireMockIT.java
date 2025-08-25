package org.acme;

import java.net.URI;
import java.net.URISyntaxException;

import jakarta.ws.rs.core.Response;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.RestClientBuilder;
import org.junit.jupiter.api.Test;

import com.github.tomakehurst.wiremock.client.WireMock;

import io.quarkiverse.wiremock.devservice.ConnectWireMock;
import io.quarkiverse.wiremock.devservice.WireMockConfigKey;
import io.quarkus.test.junit.QuarkusTest;
import se.uhr.nya.startsida.backend.control.facade.authentication.AuktoriseringService;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.assertj.core.api.Assertions.assertThat;

@QuarkusTest
@ConnectWireMock
class WireMockIT {

	WireMock wiremock;

	@ConfigProperty(name = WireMockConfigKey.PORT)
	Integer port;

	@Test
	void testHelloEndpoint() throws URISyntaxException {
		assertThat(wiremock).isNotNull();
		wiremock.register(get(urlEqualTo("/auktorisering/api/auktorisering/admin@umu.se"))
				.willReturn(aResponse().withStatus(200).withBody("{}")));

		URI apiUri = new URI("http://localhost:" + port + "/auktorisering");
		AuktoriseringService remote = RestClientBuilder.newBuilder().baseUri(apiUri).build(AuktoriseringService.class);

		try(Response response = remote.auktorisering("admin@umu.se")) {
			System.out.println(response.getStatus());
			System.out.println(response.readEntity(String.class));
		}
	}
}
