package test;

import dto.RecordRepresentation;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import org.junit.jupiter.api.Test;


public class YassonTest {

	@Test
	void shouldConvertJson() throws Exception {
		try(Jsonb jsonb = JsonbBuilder.create()) {
			RecordRepresentation newRepresentation = jsonb.fromJson("{\"message\":\"hello\"}", RecordRepresentation.class);

			System.out.println(newRepresentation);
		}
	}

}
