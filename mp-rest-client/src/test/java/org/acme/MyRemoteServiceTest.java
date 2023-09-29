package org.acme;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Set;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Test;

import org.eclipse.microprofile.rest.client.RestClientBuilder;

import static org.assertj.core.api.Assertions.assertThat;

@QuarkusTest
class MyRemoteServiceTest {

	@Inject
	@RestClient
	MyRemoteService myRemoteService;

	@Test
	void shouldInject() {
		Response response = myRemoteService.getExtensionsById("io.quarkus:quarkus-rest-client");

		assertThat(response.getStatus()).isEqualTo(200);

		Set<Extension> extensions = response.readEntity(new GenericType<Set<Extension>>() {});

		extensions.forEach(System.out::println);
	}

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