package test;

import static org.assertj.core.api.Assertions.assertThat;

import common.Gender;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;

class PersonMarshallingTest {

    private final PersonMarshalling marshalling = new PersonMarshalling();

    @Test
    void marshalsToAndFromString() {
        Person original = new Person("Bob", 30, LocalDateTime.of(1970, 1, 1, 0, 0), Map.of(Gender.MALE, Set.of("one", "two")));

        byte [] marshalled = marshalling.toByteArray(original);
        System.out.println(new String(marshalled));
        Person unmarshalled = marshalling.fromByteArray(marshalled);

        assertThat(unmarshalled).isNotSameAs(original);
        assertThat(unmarshalled).usingRecursiveComparison().isEqualTo(original);
    }
}
