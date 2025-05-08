package org.acme;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Set;
import acme.Extension;
import acme.MyRemoteService;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;

import org.eclipse.microprofile.rest.client.RestClientBuilder;

import static org.assertj.core.api.Assertions.assertThat;

class MyRemoteServiceTest {

	@Test
	void shouldCallProgrammatically() throws URISyntaxException {
		URI apiUri = new URI("https://stage.code.quarkus.io/api");
		MyRemoteService remote = RestClientBuilder.newBuilder().baseUri(apiUri).build(MyRemoteService.class);

		Response response = remote.getExtensionsById("io.quarkus:quarkus-rest-client");

		assertThat(response.getStatus()).isEqualTo(200);

		Set<Extension> extensions = response.readEntity(new GenericType<Set<Extension>>() {});

		extensions.forEach(System.out::println);
	}
}