package org.acme;

import java.net.URI;
import java.net.URISyntaxException;
import io.quarkus.test.junit.QuarkusTest;
import org.eclipse.microprofile.rest.client.RestClientBuilder;
import org.junit.jupiter.api.Test;

@QuarkusTest
class HttpClientTest {

	@Test
	void shouldCallResource() throws URISyntaxException {
		URI apiUri = new URI("http://localhost:8080");
		GreetingService service = RestClientBuilder.newBuilder()
				.baseUri(apiUri)
				.build(GreetingService.class);

		Message msg = service.hello();

		System.out.println(msg);
	}
}
