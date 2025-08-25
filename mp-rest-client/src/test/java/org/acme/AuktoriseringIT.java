package org.acme;

import java.net.URI;
import java.net.URISyntaxException;

import jakarta.ws.rs.core.Response;

import org.eclipse.microprofile.rest.client.RestClientBuilder;
import org.junit.jupiter.api.Test;

import se.uhr.nya.startsida.backend.control.facade.authentication.AuktoriseringService;

class AuktoriseringIT {

	@Test
	void shouldCall() throws URISyntaxException {

		URI apiUri = new URI("http://nya-05.nya-srv.its.umu.se:8090/auktorisering");
		AuktoriseringService remote = RestClientBuilder.newBuilder().baseUri(apiUri).build(AuktoriseringService.class);

		Response response = remote.auktorisering("admin@umu.se");

		System.out.println(response.getStatus());
		System.out.println(response.readEntity(String.class));

	}
}
