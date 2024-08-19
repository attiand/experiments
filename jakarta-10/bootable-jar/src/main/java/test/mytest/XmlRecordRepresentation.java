package test.mytest;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public record XmlRecordRepresentation(String name, String value) {

}
