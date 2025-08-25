package test.records;

import org.junit.jupiter.api.Test;

import com.thoughtworks.xstream.XStream;

import test.Address;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class PersonAliasTest {

	@Test
	void shouldUseAlias() {
		var person = new PersonAlias("John", "Doe", new Address("Baker Street"));

		XStream xstream = new XStream();
		xstream.autodetectAnnotations(true);
		String xml = xstream.toXML(person);
		assertThat(xml).contains("givenName");
		System.out.println(xml);
	}
}