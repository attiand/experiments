package test;

import org.instancio.Instancio;
import org.instancio.Model;
import org.instancio.settings.Keys;
import org.instancio.settings.Settings;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.instancio.Select.all;
import static org.instancio.Select.field;

class SimpleRecordInstanceioTest {

    @Test
    void shouldCreateRandomPerson() {
        SimpleRecord simple = Instancio.create(SimpleRecord.class);

        System.out.println(simple);
        assertThat(simple).isInstanceOf(SimpleRecord.class);
    }

}