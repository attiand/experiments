package clone;

import org.junit.jupiter.api.Test;

import test.Address;
import test.Person;

import static org.assertj.core.api.Assertions.assertThat;

class DeepClonerTest {

	@Test
	void shouldCloneObject() {
		var original = Person.of("john", "doe", new Address("Maple street"));

		var clone = new DeepCloner().deepClone(original, Person.class);

		assertThat(clone).usingRecursiveComparison().isEqualTo(original);
	}
}