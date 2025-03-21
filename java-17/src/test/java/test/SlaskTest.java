package test;

import java.util.Objects;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

class SlaskTest {

	@Test
	void shouldPrintNull() throws Exception {

		System.out.println(SlaskTest.class.getPackageName());

		assertThat(Objects.toString(null)).isEqualTo("null");

		System.out.println(Objects.toString(null) + "hell");
	}
}
