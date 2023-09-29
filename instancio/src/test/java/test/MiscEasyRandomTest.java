package test;

import java.util.Map;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MiscEasyRandomTest {

	static class Example {
		Map<String, String> options;
	}

	@Test
	void shouldCreateMap() {
		EasyRandom easyRandom = new EasyRandom();
		Example example= easyRandom.nextObject(Example.class);

		System.out.println(example);

		assertThat(example).isInstanceOf(Example.class);
	}
}
