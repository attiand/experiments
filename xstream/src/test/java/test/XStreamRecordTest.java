package test;

import com.thoughtworks.xstream.XStream;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class XStreamRecordTest {

	@Test
	void shouldConvertRecordToXml() {
		XStream xstream = new XStream();
		var person = new Person("John", "Doe", new Address("Baker Street"));
		System.out.println(xstream.toXML(person));
	}

	@Test
	void shouldConvertMixedToXml() {
		XStream xstream = new XStream();
		var person = new Mixed(new Person("John", "Doe", new Address("Baker Street")),
				new PersonClazz("John", "Doe", new Address("Baker Street")));
		System.out.println(xstream.toXML(person));
	}

	@Test
	void shouldConvertRecordToXmlAndBack() {
		XStream xstream = new XStream();
		var person = new Person("John", "Doe", new Address("Baker Street"));
		String xml = xstream.toXML(person);

		var person2 = (Person) xstream.fromXML(xml);

		assertThat(person.firstName()).isEqualTo(person2.firstName());
	}
}