package test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.instancio.Instancio;
import org.instancio.Model;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.instancio.Select.all;
import static org.instancio.Select.field;

class InstancioModelTest {

	Model<Person> model1 = Instancio.of(Person.class)
			.ignore(field(Person::getFirstName))
			.supply(all(Address.class),
					random -> new Address(random.mixedCaseAlphabetic(3), random.oneOf(1, 3, 4, 6), random.digits(3)))
			.toModel();

	Model<Address> model2 = Instancio.of(Address.class).toModel();

	static class InstancioFactory {

		private final Map<Class<?>, Model<?>> registry;

		record Entry<T>(Class<T> clazz, Model<T> model) {

		}

		static <T> Entry<T> entry(Class<T> clazz, Model<T> model) {
			return new Entry<>(clazz, model);
		}

		private InstancioFactory(Map<Class<?>, Model<?>> registry) {
			this.registry = registry;
		}

		static InstancioFactory of(Entry<?>... entries) {
			return new InstancioFactory(Arrays.stream(entries).collect(Collectors.toMap(Entry::clazz, Entry::model)));
		}

		@SuppressWarnings("unchecked")
		<T> Model<T> create(Class<T> clazz) {
			return (Model<T>) registry.get(clazz);
		}
	}

	InstancioFactory factory = InstancioFactory.of(InstancioFactory.entry(Person.class, model1),InstancioFactory.entry(Address.class, model2));

	@Test
 	void shouldUseModel() {
		List<Class<?>> classes = List.of(Person.class, Address.class);

		classes.stream().map(c -> factory.create(c)).forEach(i -> {
			System.out.println(i);
		});

		assertThat(factory.create(Person.class)).isInstanceOf(Person.class);
	}
}
