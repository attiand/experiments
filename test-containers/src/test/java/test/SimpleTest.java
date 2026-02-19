package test;

import jakarta.json.JsonObject;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.FixedHostPortGenericContainer;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.Network;

import java.net.URI;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class SimpleTest {

    private final static String IMAGE = "docker.nya-srv.its.umu.se/sim-ladok3:3.19.0";

    @Test
    void shouldStartContainer() throws InterruptedException {
        try (var container = new GenericContainer<>(IMAGE).withExposedPorts(8090)
                .withEnv("SIMONE_BASE_URI", "http://sim-ladok3:%s".formatted(8090))
                .withEnv("QUARKUS_HTTP_PORT", Integer.toString(8090))) {

            System.out.println("-- use nw " + container.getNetwork());

            container.start();

            URI base = UriBuilder.newInstance().scheme("http").host(container.getHost()).port(container.getMappedPort(8090)).build();

            URI uri = UriBuilder.fromUri(base).path("application/version").build();

            System.out.println(" -- " + uri);

            checkVersion(uri);
        }
    }

    private void checkVersion(URI uri) {
        try (Client client = ClientBuilder.newBuilder().build()) {
            try (Response response = client.target(uri).request(MediaType.APPLICATION_JSON).get()) {
                var status = response.getStatusInfo();

                JsonObject version = response.readEntity(JsonObject.class);

                System.out.println(version.get("implementation"));
            }
        }
    }
}
