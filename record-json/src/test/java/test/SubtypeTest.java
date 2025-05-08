package test;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import org.junit.jupiter.api.Test;
import subtype.jackson.Animal;
import subtype.jackson.Cat;
import subtype.jsonb.Car;
import subtype.jsonb.Vehicle;


class SubtypeTest {

	private static final Jsonb jsonb = JsonbBuilder.create();

	private static final ObjectMapper objectMapper = new ObjectMapper();

	@Test
	void shouldConvertToJsonAndBackWithJsonb() {
		var car = new Car("Volvo");

		var str = jsonb.toJson(car);
		System.out.println(jsonb.toJson(car));

		var converted = jsonb.fromJson(str, Vehicle.class);
		System.out.println(converted);
	}

	@Test
	void shouldConvertToJsonAndBackWithJackson() throws JsonProcessingException {
		var cat = new Cat("Mats");
		var str = objectMapper.writeValueAsString(cat);
		System.out.println(str);

		var converted = objectMapper.readValue(str, Animal.class);
		System.out.println(converted);
	}
}
