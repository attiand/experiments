package test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class HttpClientPutTest {

    private static final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_1_1)
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    public static void main(String[] args) throws IOException, InterruptedException {
        var product = new Product();
        product.setName("legacy");
        product.setVersion("master");

        HttpRequest request = HttpRequest.newBuilder()
                .PUT(JacksonBodyPublishers.asJSON(product))
                .uri(URI.create("http://localhost:8080/hello"))
                .setHeader("User-Agent", "Java 11 HttpClient Bot") // add request header
                .setHeader("Content-Type", "application/json")
                .build();

        HttpResponse<Void> response = httpClient.send(request, HttpResponse.BodyHandlers.discarding());

        System.out.println(response.statusCode());

    }
}
