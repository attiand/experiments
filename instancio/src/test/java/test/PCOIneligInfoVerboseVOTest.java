package test;

import org.instancio.Instancio;
import org.junit.jupiter.api.Test;

class PCOIneligInfoVerboseVOTest {

	@Test
	void shouldConvert() {
		var original = Instancio.of(PCOIneligInfoVerboseVO.class).create();
	}
}
