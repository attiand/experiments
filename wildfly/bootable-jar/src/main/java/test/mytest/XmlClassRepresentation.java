package test.mytest;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class XmlClassRepresentation {

	private String name;
	private String value;

	public XmlClassRepresentation() {
	}

	public XmlClassRepresentation(String name, String value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "XmlRepresentation [name=" + name + ", value=" + value + "]";
	}
}
