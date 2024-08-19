package test;

import org.instancio.Instancio;
import org.instancio.Select;
import org.instancio.junit.InstancioExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(InstancioExtension.class)
class InstancioGetFieldTest {

	@Test
	void shouldUseGetter() {
		var original = Instancio.of(SimpleRecord.class)
				.set(Select.field(SimpleRecord::firstName), "joe").create();

		System.out.println(original);
	}
}
