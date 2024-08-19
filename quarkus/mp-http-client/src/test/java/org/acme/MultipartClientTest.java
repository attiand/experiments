package org.acme;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.ws.rs.core.EntityPart;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.RestClientBuilder;
import org.junit.jupiter.api.Test;

@QuarkusTest
class MultipartClientTest {

	@Test
	void shouldPostFile() throws URISyntaxException, IOException {
		URI apiUri = new URI("http://localhost:8080");
		MultipartService service = RestClientBuilder.newBuilder()
				.baseUri(apiUri)
				.build(MultipartService.class);

		try(InputStream is = getClass().getResourceAsStream("/test.txt")) {
			var part = EntityPart.withName("data").fileName("myfile").content(is).build();

			try(Response response = service.postFileV1(List.of(part))) {
				System.out.println(response.getStatus());
			}
		}
	}
}
