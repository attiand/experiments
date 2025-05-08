package test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Properties;

public class IntegrationTestProperties {

	static final String PROPERTIES_FILE = ".integration-test.properties";

	private final Properties properties;

	public enum Config {
		DB_NAME("db.name", "DB_NAME"),
		DB_TARGET("db.target", "DB_TARGET"),
		DB_USER("db.user", "DB_USER"),
		DB_PASSWD("db.passwd", "DB_PASSWD");

		private final String propertyName;
		private final String environmentVariable;

		Config(String propertyName, String envVariable) {
			this.propertyName = propertyName;
			this.environmentVariable = envVariable;
		}

		public String propertyName() {
			return propertyName;
		}

		public String environmentVariable() {
			return environmentVariable;
		}
	}

	public IntegrationTestProperties() {
		this(Paths.get("."));
	}

	IntegrationTestProperties(Path cwd) {

		// prio 3, properties from local file
		try {
			properties = propertiesFromFile(cwd);
		} catch (IOException e) {
			throw new IntegrationTestPropertiesException("Failed to load properties file", e);
		}

		// prio 2, environment variable
		Arrays.stream(Config.values()).forEach(config -> {
			String value = System.getenv(config.environmentVariable());
			if (value != null) {
				properties.put(config.propertyName(), value);
			}
		});

		// prio 1, system properties
		System.getProperties().forEach((key, value) -> properties.put(key.toString(), value.toString()));

		var missing = Arrays.stream(Config.values()).filter(config -> !properties.containsKey(config.propertyName())).toList();

		if (!missing.isEmpty()) {
			throw new IntegrationTestPropertiesException(
					"Missing integration-test properties: " + missing.stream().map(Config::propertyName).toList());
		}
	}

	public String getProperty(Config key) {
		return properties.getProperty(key.propertyName());
	}

	private static Properties propertiesFromFile(Path cwd) throws IOException {
		Path root = getMavenRootDirectory(cwd);

		if (root == null) {
			throw new IntegrationTestPropertiesException("Could not find maven root directory");
		}

		Path testProperties = root.resolve(PROPERTIES_FILE);

		Properties properties = new Properties();

		if (Files.exists(testProperties) && Files.isRegularFile(testProperties)) {
			try (var reader = Files.newBufferedReader(testProperties, StandardCharsets.UTF_8)) {
				properties.load(reader);
			}
		}
		return properties;
	}

	private static Path getMavenRootDirectory(Path startDirectory) {
		var dir = startDirectory.toAbsolutePath();
		while (dir != null) {
			if (Files.isDirectory(dir) && Files.exists(dir.resolve(".mvn"))) {
				return dir;
			}
			dir = dir.getParent();
		}
		return null;
	}

	public static class IntegrationTestPropertiesException extends RuntimeException {

		public IntegrationTestPropertiesException(String message) {
			super(message);
		}

		public IntegrationTestPropertiesException(String message, Throwable cause) {
			super(message, cause);
		}
	}
}
