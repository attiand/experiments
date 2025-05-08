package test;

import com.thoughtworks.xstream.XStream;

public class XStreamConverter {

	public String convert(XmlObject obj) {
		XStream xstream = new XStream();
		return xstream.toXML(obj);
	}

}
