package test;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationFileHandlerTest {

	@Test
	void shouldWriteObject(@TempDir Path dir) throws IOException {
		var application = new Application(LocalDate.now(), "test", new MyTest(true));
		var fileHandler = new ApplicationFileHandler();
		Path file = dir.resolve("test.json");

		fileHandler.save(application, file.toFile());

		var converted = fileHandler.load(file.toFile());

		assertEquals(application.startDate(), converted.startDate());



	}
}