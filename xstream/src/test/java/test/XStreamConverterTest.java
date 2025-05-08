package test;

import java.util.ArrayList;
import java.util.List;
import com.thoughtworks.xstream.XStream;
import org.junit.jupiter.api.Test;

public class XStreamConverterTest {

	@Test
	void shouldConvertToXmlAndBack() {
		XStream xstream = new XStream();
		var person = new XmlPojo("John", "Doe");
		String xml = xstream.toXML(person);
		System.out.println(xstream.fromXML(xml));
	}

	@Test
	void shouldConvertListOfRecords() {
		XStream xstream = new XStream();

		xstream.allowTypesByWildcard(new String[] {
				"test.**"
		});

		List<PersonClazz> original = new ArrayList<>(List.of(new PersonClazz("John", "Doe", new Address("Main Street")),
				new PersonClazz("Jane2", "Doe2", new Address("Main Street 2"))));

		String xml = xstream.toXML(original);

		System.out.println(xml);

		Object obj = xstream.fromXML(xml);

		System.out.println(obj);
	}
}
