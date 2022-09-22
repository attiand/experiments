package test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BasicIsoLocalDateAdapter extends XmlAdapter<String, LocalDate> {

	private static final Logger LOG = LoggerFactory.getLogger(BasicIsoLocalDateAdapter.class);

	private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.BASIC_ISO_DATE;

	@Override
	public String marshal(LocalDate value) {
		LOG.info("jaxb marshal");
		if (value != null) {
			return value.format(DATE_FORMATTER);
		}
		return null;
	}

	@Override
	public LocalDate unmarshal(String s) {
		LOG.info("jaxb unmarshal");
		try {
			return LocalDate.parse(s, DATE_FORMATTER);
		} catch (DateTimeParseException e) {
			throw new IllegalArgumentException(e);
		}
	}
}
