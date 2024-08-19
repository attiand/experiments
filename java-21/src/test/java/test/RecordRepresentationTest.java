package test;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecordRepresentationTest {

	@Test
	void shouldCreateRecord() {
		var record = RecordRepresentation.of("message");
		System.out.println(record);
	}
}