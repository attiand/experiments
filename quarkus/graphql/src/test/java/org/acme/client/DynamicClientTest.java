package org.acme.client;

import io.quarkus.test.junit.QuarkusTest;
import io.smallrye.graphql.client.Response;
import io.smallrye.graphql.client.core.Document;

import static io.smallrye.graphql.client.core.Document.document;
import static io.smallrye.graphql.client.core.Field.field;
import static io.smallrye.graphql.client.core.Operation.operation;
import static org.assertj.core.api.Assertions.assertThat;

import io.smallrye.graphql.client.GraphQLClient;
import io.smallrye.graphql.client.core.OperationType;
import io.smallrye.graphql.client.dynamic.api.DynamicGraphQLClient;
import jakarta.inject.Inject;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;

@QuarkusTest
class DynamicClientTest {

    @Inject
    @GraphQLClient("person-dynamic")
    DynamicGraphQLClient dynamicClient;

    @Test
    void shouldGetPersons() throws ExecutionException, InterruptedException {
        Document query = document(
                operation(OperationType.QUERY, "all",
                        field("persons",
                                field("firstName")
                        )
                )
        );

        Response response = dynamicClient.executeSync(query);

        assertThat(response.hasError()).isFalse();

        JsonObject root = response.getData();
        JsonArray persons = root.getJsonArray("persons");

        assertThat(persons).hasSize(2);
        assertThat(persons.getJsonObject(0).getString("firstName")).isEqualTo("John");
        assertThat(persons.getJsonObject(1).getString("firstName")).isEqualTo("Bruce");
    }
}
