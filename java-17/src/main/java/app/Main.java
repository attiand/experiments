package app;

import java.util.Properties;

public class Main {

	private static final Properties props = new Properties();

	public static void main(String[] args) {
		System.setProperty("system", "bar");
		props.setProperty("foo", "bar");
		System.setProperties(props);
	}
}
