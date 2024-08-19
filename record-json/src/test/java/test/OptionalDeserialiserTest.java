package test;

import convert.WithOptional;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import org.junit.jupiter.api.Test;


class OptionalDeserialiserTest {

	private static Jsonb jsonb = JsonbBuilder.create();

	@Test
	void shouldDeserilizeOptional() {
		var dao = new WithOptional("test");

		var str = jsonb.toJson(dao);

		System.out.println(str);

		var converted = jsonb.fromJson(str, WithOptional.class);

		System.out.println(converted);
	}
}