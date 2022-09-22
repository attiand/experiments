package test;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import org.junit.jupiter.api.Test;

import com.github.tomakehurst.wiremock.client.WireMock;

class WireMockTest {

	@Test
	void shouldMock() throws IOException {
		WireMock wm = new WireMock("localhost", 8090);

		try (InputStream is = WireMockTest.class.getResourceAsStream("/lyckadbetalning1.xml")) {
			wm.register(get(urlEqualTo("/endpoint?param=1"))
					.willReturn(aResponse().withHeader("Content-Type", "application/xml").withBody(is.readAllBytes())));
		}

		try (InputStream is = WireMockTest.class.getResourceAsStream("/lyckadbetalning1.xml")) {
			wm.register(get(urlEqualTo("/endpoint?param=2"))
					.willReturn(aResponse().withHeader("Content-Type", "application/xml").withStatus(500)));
		}
	}

	@Test
	void shouldGetParam1() throws IOException, InterruptedException {
		HttpClient httpClient =
				HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1).connectTimeout(Duration.ofSeconds(10)).build();

		HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://localhost:8090/endpoint?param=1")).GET().build();

		HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
		System.out.println(response.statusCode());
		System.out.println(response.body());
	}

	@Test
	void shouldGetParam2() throws IOException, InterruptedException {
		HttpClient httpClient =
				HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1).connectTimeout(Duration.ofSeconds(10)).build();

		HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://localhost:8090/endpoint?param=2")).GET().build();

		HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
		System.out.println(response.statusCode());
	}
}
