package test;

import org.instancio.Instancio;
import org.instancio.Model;
import org.instancio.settings.Keys;
import org.instancio.settings.Settings;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.instancio.Select.all;
import static org.instancio.Select.field;

class PersonInstanceioTest {

    @Test
    void shouldCreateRandomPerson() {
        Person person = Instancio.create(Person.class);

        System.out.println(person);
        assertThat(person).isInstanceOf(Person.class);
    }

    @Test
    void shouldCreateRandomAddress() {
        Address address = Instancio.create(Address.class);

        System.out.println(address);
        assertThat(address).isInstanceOf(Address.class);
    }

    @Test
    void genericComposedShouldBeCorrectlyPopulated() {
        // given

        // when
        IdResource.CompositeResource composite = Instancio.create(IdResource.CompositeResource.class);

        // then
        assertThat(composite.longResource.getId()).isInstanceOf(Long.class);
    }

    @Test
    void shouldCreateRandomAddressWithBeanValidation() {
        Settings settings = Settings.create()
                .set(Keys.BEAN_VALIDATION_ENABLED, true);

        Address address = Instancio.of(Address.class).withSettings(settings).create();

        System.out.println(address);

        assertThat(address).isInstanceOf(Address.class);
    }

    @Test
    void shouldCreateRandomCustomPerson() {
        Person person = Instancio.of(Person.class)
                .set(field(Person.class, "address"),  new Address("mystreet", 1, "code"))
                .set(all(String.class), "mystring")
                .create();

        System.out.println(person);
		assertThat(person.getAddress().street()).isEqualTo("mystreet");
    }

	@Test
	void shouldCreateRandomCustomPerson2() {
		Person person = Instancio.of(Person.class)
				.set(field(Person::getAddress),  new Address("mystreet", 1, "code"))
				.set(all(String.class), "mystring")
				.create();

		System.out.println(person);
		assertThat(person.getAddress().street()).isEqualTo("mystreet");
	}


	@Test
	void shouldCreateRandomCustomAddress() {
		var address = Instancio.of(Address.class)
				.set(field(Address::street), "mystreet")
				.create();

		System.out.println(address);
		assertThat(address.street()).isEqualTo("mystreet");
	}


    Model<Address> createAddressModel() {
        return Instancio.of(Address.class).set(field(Address.class, "street"), "mystreet").toModel();
    }

    Model<Person> createPersonModel() {
        return Instancio.of(Person.class)
                .set(field(Person.class, "address"),  Instancio.of(createAddressModel()).create())
                .set(all(String.class), "mystring")
                .toModel();
    }

    @Test
    void shouldCreatModel() {
        Model<Person> personModel = createPersonModel();

        Person person = Instancio.of(personModel).create();

        System.out.println(person);
        assertThat(person.getAddress().street()).isEqualTo("mystreet");
    }

}