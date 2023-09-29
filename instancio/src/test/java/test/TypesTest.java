package test;

import java.util.UUID;
import org.instancio.junit.InstancioSource;
import org.junit.jupiter.params.ParameterizedTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class TypesTest {

	@ParameterizedTest
	@InstancioSource
	void multipleArguments(short s) {
		var types = new Types(s);
		assertThat(types.s()).isBetween(Short.MIN_VALUE, Short.MAX_VALUE);
	}

}