package test;

import static org.assertj.core.api.Assertions.assertThat;

import common.Gender;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

class PersonMarshallingTest {

    private final PersonMarshalling marshalling = new PersonMarshalling();

    @Test
    void marshalsToAndFromString() {
        Person original = new Person("Bob", 30, Map.of(Gender.MALE, new IntList(List.of(2, 4))));

        byte [] marshalled = marshalling.toByteArray(original);
        System.out.println(new String(marshalled));
        Person unmarshalled = marshalling.fromByteArray(marshalled);

        assertThat(unmarshalled).usingRecursiveComparison().isEqualTo(original);
    }
}
