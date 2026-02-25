package org.acme.client;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.acme.serverside.Person;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


import java.util.List;

@QuarkusTest
public class TypesafeClientTest {

    @Inject
    PersonAPI person;

    @Test
    void shouldGetAllPersons() {
        List<Person> persons = person.persons();
        assertThat(persons).hasSize(2).map(Person::firstName).containsExactly("John", "Bruce");
    }

    @Test
    void shouldGetSpecificPerson() {
        Person p1 = person.getPerson(1);
        assertThat(p1.lastName()).isEqualTo("Doe");
    }
}
