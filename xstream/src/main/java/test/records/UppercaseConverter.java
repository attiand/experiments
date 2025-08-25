package test.records;

import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.converters.SingleValueConverter;

@XStreamConverter()
public class UppercaseConverter implements SingleValueConverter {

	@Override
	public String toString(Object obj) {
		return obj.toString().toUpperCase();
	}

	@Override
	public Object fromString(String str) {
		return str.toLowerCase();
	}

	@Override
	public boolean canConvert(Class type) {
		return type == String.class;
	}
}
