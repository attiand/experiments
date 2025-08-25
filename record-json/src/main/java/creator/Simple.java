package creator;

import jakarta.json.bind.annotation.JsonbCreator;

public class Simple {

	private final String name;

	public Simple(String name) {
		this.name = name;
	}

	@JsonbCreator
	public static Simple create(String arg0) {
		return new Simple(arg0);
	}

	public String getName() {
		return name;
	}
}
