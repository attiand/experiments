package test;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.EntityPart;
import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MultiPartTest {

	@Test
	void shouldPerformMultiPartRequest() throws IOException {
		//URI uri = UriBuilder.fromUri("http://localhost:8080").path("order/file").build();
		URI uri = UriBuilder.fromUri("http://localhost:8080").path("admin/database").build();

		var path = Paths.get("src/main/resources/order.txt");

		try(InputStream is  = Files.newInputStream(path)) {
			final List<EntityPart> multipart =
					List.of(EntityPart.withName("name").content(path.toString().getBytes()).mediaType(MediaType.TEXT_PLAIN_TYPE).build(),
							EntityPart.withName("content").content(is).mediaType(MediaType.APPLICATION_OCTET_STREAM_TYPE).build());

			try (Client client = ClientBuilder.newBuilder().register(ClientResponseLogFilter.class).build()) {
				try (Response response = client.target(uri).request(MediaType.APPLICATION_JSON_TYPE).post(Entity.entity(new GenericEntity<>(multipart) {

				}, MediaType.MULTIPART_FORM_DATA_TYPE))) {
					var status = response.getStatusInfo();
					assertThat(status.getFamily()).isEqualTo(Response.Status.Family.SUCCESSFUL);
				}
			}
		}
	}
}
