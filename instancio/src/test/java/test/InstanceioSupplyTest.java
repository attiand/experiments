package test;

import org.instancio.Instancio;
import org.instancio.Select;
import org.instancio.junit.InstancioExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(InstancioExtension.class)
class InstanceioSupplyTest {


	@Test
	void shouldCreateRandomAddress() {

		var myAddress = new Address("street", 19, "country");

		Address address = Instancio.of(Address.class).supply(Select.root(), gen -> myAddress).create();

		assertThat(address).isSameAs(myAddress);
	}

	@Test
	void shouldCreateSimpleClass() {

		var simple = new SimpleClass("street");

		SimpleClass mySimple = Instancio.of(SimpleClass.class).supply(Select.root(), gen -> simple).create();

		System.out.println(mySimple);

		assertThat(simple).isEqualTo(mySimple);
		assertThat(simple).isSameAs(mySimple);
	}
}