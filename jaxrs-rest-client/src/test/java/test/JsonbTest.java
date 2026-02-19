package test;

import java.net.URI;

import jakarta.json.JsonObject;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class JsonbTest {

    @Test
    void shouldHandleJsonB() {
        System.out.println("test");

        URI uri = UriBuilder.fromUri("http://nya-01.nya-srv.its.umu.se:7080/DocumentService/service/file/ping").build();

        try(Client client = ClientBuilder.newBuilder().register(ClientResponseLogFilter.class).build()) {
            try (Response response =
                         client.target(UriBuilder.fromUri("http://nya-01.nya-srv.its.umu.se:7080/DocumentService/service/file/ping")).request(MediaType.APPLICATION_JSON).get()) {
                if (response.getStatus() == Response.Status.OK.getStatusCode()) {

                    DummyResultVO body = response.readEntity(DummyResultVO.class);

                    System.out.println(body);

                }
            }
        }
    }
}
