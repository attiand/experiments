package test;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;

public class ApplicationFileHandler {

	private final Jsonb jsonb = JsonbBuilder.create();

	void save(Application application, File file) throws IOException {
		try (var writer = new FileWriter(file)) {
			jsonb.toJson(application, writer);
		}
	}

	Application load(File file) throws IOException {
		try (var reader = new FileReader(file)) {
			return jsonb.fromJson(reader, Application.class);
		}
	}
}
