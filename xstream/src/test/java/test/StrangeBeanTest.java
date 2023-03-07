package test;

import com.thoughtworks.xstream.XStream;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StrangeBeanTest {

    @Test
    void shouldGenerateXml() {
        XStream xstream = new XStream();
        var person = new StrangeBean();
        person.setFirstName("Mattias");
        person.setLastName("Andersson");

        System.out.println(xstream.toXML(person));
    }
}