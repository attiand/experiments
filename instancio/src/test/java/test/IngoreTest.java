package test;

import org.instancio.Instancio;
import org.instancio.Select;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class IngoreTest {

    @Test
    void shouldIngore() {
        Person person = Instancio.of(Person.class).ignore(Select.field(Person::getAddress)).create();

        System.out.println(person);
    }

    @Test
    void shouldSetToNull() {
        Person person = Instancio.of(Person.class).set(Select.field(Person::getAddress), null).create();

        System.out.println(person);
    }
}