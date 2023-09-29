package test;

import java.util.UUID;
import org.instancio.Instancio;
import org.instancio.junit.InstancioExtension;
import org.instancio.junit.InstancioSource;
import org.instancio.junit.WithSettings;
import org.instancio.settings.Keys;
import org.instancio.settings.Settings;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(InstancioExtension.class)
class InstanceioParameterTest {

    @ParameterizedTest
    @InstancioSource
    void shouldCreateRandomAddress(Address address) {
        System.out.println(address);
        assertThat(address).isInstanceOf(Address.class);
    }

    @ParameterizedTest
    @InstancioSource
    void multipleArguments(String str, UUID uuid, Person foo) {
        System.out.println(foo);
        assertThat(foo).isInstanceOf(Person.class);
    }
}