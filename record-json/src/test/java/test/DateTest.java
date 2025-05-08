package test;

import java.time.LocalDate;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.json.bind.JsonbConfig;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class DateTest {

	private static final JsonbConfig config = new JsonbConfig().withFormatting(true);
	private static final Jsonb JSONB = JsonbBuilder.create(config);

	@Test
	void shouldConvertDate() {
		var org = new Application(LocalDate.now(), "test", new MyTest(true));

		var str = JSONB.toJson(org);

		System.out.println(str);

		var converted = JSONB.fromJson(str, Application.class);

		assertThat(converted).usingRecursiveComparison().isEqualTo(org);

		assertThat(converted.startDate()).isEqualTo(org.startDate());
	}

	@Test
	void shouldConvertDateToFile() {
		var org = new Application(LocalDate.now(), "test", new MyTest(true));

		var str = JSONB.toJson(org);

		System.out.println(str);

		var converted = JSONB.fromJson(str, Application.class);

		assertThat(converted).usingRecursiveComparison().isEqualTo(org);

		assertThat(converted.startDate()).isEqualTo(org.startDate());
	}
}
