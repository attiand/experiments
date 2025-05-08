package mytest;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

class SimpleTest {

	@Test
	void testName() throws Exception {
		assertThat(true).isTrue();
	}

}
