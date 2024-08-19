package test;

import org.junit.jupiter.api.Test;

class ConverterTest {

	@Test
	void shouldConvertPojo() {
		Converter converter = new Converter();
		var person = new XmlPojo("John", "Doe");
		System.out.println(converter.convert(person));
	}

	static class XmlString extends String implements XmlObject {
		private final String value;

		public XmlString(String value) {
			this.value = value;
		}
	}

	@Test
	void shouldConvertString() {
		Converter converter = new Converter();
		var person = new XmlPojo("John", "Doe");
		System.out.println(converter.convert(person));
	}
}