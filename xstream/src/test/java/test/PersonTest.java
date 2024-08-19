package test;

import com.thoughtworks.xstream.XStream;
import org.junit.jupiter.api.Test;

class PersonTest {
    @Test
    void shouldConvertRecordToXml() {
        XStream xstream = new XStream();
        var person = new Person("John", "Doe", new Address("Baker Street"));
        System.out.println(xstream.toXML(person));
    }
}