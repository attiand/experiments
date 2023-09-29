package test;

import java.time.LocalDateTime;
import org.instancio.Instancio;
import org.instancio.junit.InstancioExtension;
import org.instancio.junit.WithSettings;
import org.instancio.settings.Keys;
import org.instancio.settings.Settings;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;
import static org.instancio.Select.all;
import static org.instancio.Select.field;

@ExtendWith(InstancioExtension.class)
class InstanceioTest {

	@WithSettings
	private final Settings settings = Settings.create().set(Keys.INTEGER_MAX, 100).set(Keys.STRING_NULLABLE, true);

	@Test
	void shouldCreateRandomAddress() {
		Address address = Instancio.create(Address.class);

		System.out.println(address);
		assertThat(address).isInstanceOf(Address.class);
	}


}