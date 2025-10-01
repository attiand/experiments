package org.acme;

import java.net.URI;
import java.net.URISyntaxException;
import acme.MyRemoteService;
import acme.Version;

import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;

import org.eclipse.microprofile.rest.client.RestClientBuilder;

import static org.assertj.core.api.Assertions.assertThat;

class MyRemoteServiceTest {

	@Test
	void shouldCallProgrammatically() throws URISyntaxException {
		URI apiUri = new URI("http://nya-01.nya-srv.its.umu.se:61080/api");
		MyRemoteService remote = RestClientBuilder.newBuilder().baseUri(apiUri).build(MyRemoteService.class);

		Response response = remote.version();

		assertThat(response.getStatus()).isEqualTo(200);

		Version version = response.readEntity(Version.class);

        System.out.println(version.scm());
		System.out.println(version);
	}
}