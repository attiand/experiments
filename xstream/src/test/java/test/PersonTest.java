package test;

import com.thoughtworks.xstream.XStream;
import org.junit.jupiter.api.Test;

class PersonTest {
    @Test
    void shouldConvertRecordToXml() {
        XStream xstream = new XStream();
        var person = new Person("Mattias", "Andersson", new Address("Björkågatan"));
        System.out.println(xstream.toXML(person));
    }
}