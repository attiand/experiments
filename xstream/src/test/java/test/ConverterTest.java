package test;

import com.thoughtworks.xstream.XStream;
import org.junit.jupiter.api.Test;

class ConverterTest {

	@Test
	void shouldConvertPojo() {
		XStreamConverter converter = new XStreamConverter();
		var person = new XmlPojo("John", "Doe");
		System.out.println(converter.convert(person));
	}



	@Test
	void shouldConvertString() {
		XStream xstream = new XStream();
		var person = new XmlPojo("John", "Doe");
		System.out.println(xstream.toXML(person));
	}
}