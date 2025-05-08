package app;

public final class Bar {

	private Bar() {
	}

	public static String setProperty(String key, String value) {
		System.out.println("foo: " + key + " = " + value);
		return key;
	}
}
