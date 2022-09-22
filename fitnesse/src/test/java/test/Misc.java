package test;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;

class Misc {

	@Test
	void shouldMock() throws IOException {
		System.out.println(List.of(12).stream().findFirst());
	}

}
