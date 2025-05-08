package test;

import io.quarkus.runtime.annotations.RegisterForReflection;
import org.acme.Message;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AnnotationTest {

	@Test
	void shouldFindAnnotationOnClass() {
		Class<Message> clazz = Message.class;

		assertThat(clazz.isAnnotationPresent(RegisterForReflection.class)).isTrue();
	}
}
