package test;

import org.junit.jupiter.api.Test;

class RecordTest {

	@Test
	void shouldUseToString() {
		var simple = new SimpleRecord("john", "doe");

		System.out.println(simple);
	}
}
