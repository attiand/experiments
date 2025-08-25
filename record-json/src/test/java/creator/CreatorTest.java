package creator;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;

import org.junit.jupiter.api.Test;

class CreatorTest {

	private static final Jsonb jsonb = JsonbBuilder.create();

	@Test
	void shouldSerilizeAndBack() {

		var json = jsonb.toJson(new SubClazz("test", "sub"));

		System.out.println(json);

		var converted = jsonb.fromJson(json, SubClazz.class);

		System.out.println(converted.getName());
		System.out.println(converted.getSubName());
	}

	@Test
	void simple() {
		var json = jsonb.toJson(new Simple("test"));

		System.out.println(json);

		var converted = jsonb.fromJson(json, Simple.class);

		System.out.println(converted.getName());
	}
}
