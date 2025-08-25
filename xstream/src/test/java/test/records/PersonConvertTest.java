package test.records;

import org.junit.jupiter.api.Test;

import com.thoughtworks.xstream.XStream;

import test.Address;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class PersonConvertTest {

	@Test
	void shouldUseConverter() {
		var person = new PersonConverter("John", "Doe", new Address("Baker Street"));

		XStream xstream = new XStream();
		xstream.autodetectAnnotations(true);
		xstream.registerConverter(new UppercaseConverter());
		String xml = xstream.toXML(person);
		assertThat(xml).contains("JOHN");
		System.out.println(xml);
	}
}