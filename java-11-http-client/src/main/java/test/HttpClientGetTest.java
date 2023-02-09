package test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.function.Supplier;

public class HttpClientGetTest {

    private static final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_1_1)
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    public static void main(String[] args) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://localhost:8080/hello"))
                .setHeader("User-Agent", "Java 11 HttpClient Bot") // add request header
                .build();


        HttpRequest.BodyPublishers.ofString("asd");

        HttpResponse<Supplier<Product>> response = httpClient
                .send(request, JacksonBodyHandlers.asJSON(Product.class));

        System.out.println(response.statusCode());
        System.out.println(response.body().get().getName());

    }
}
