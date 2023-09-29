package test;

import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class PersonEasyRandomTest {

    @Test
    void shouldCreateRandomPerson() {
        EasyRandom easyRandom = new EasyRandom();
        Person person = easyRandom.nextObject(Person.class);

        System.out.println(person);

        assertThat(person).isInstanceOf(Person.class);
    }

    @Test
    void shouldCreateRandomAddress() {
        EasyRandom easyRandom = new EasyRandom();
        Address address = easyRandom.nextObject(Address.class);

        System.out.println(address);

        assertThat(address).isInstanceOf(Address.class);
    }

    @Test
    void genericComposedShouldBeCorrectlyPopulated() {
        // given
        EasyRandom easyRandom = new EasyRandom();

        // when
        IdResource.CompositeResource composite = easyRandom.nextObject(IdResource.CompositeResource.class);

        // then
        assertThat(composite.longResource.getId()).isInstanceOf(Long.class);
    }
}