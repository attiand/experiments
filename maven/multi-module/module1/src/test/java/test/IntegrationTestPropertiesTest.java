package test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import test.IntegrationTestProperties.Config;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class IntegrationTestPropertiesTest {

	@Test
	void shouldGetPropertiesFromFile(@TempDir Path dir) throws IOException {
		Files.createDirectories(dir.resolve(".mvn"));
		var module1 = dir.resolve("module1");
		Files.createDirectories(module1);

		var content = List.of("db.name=myname", "db.target=nya-01", "db.user=me", "db.passwd=secret");

		Files.write(dir.resolve(IntegrationTestProperties.PROPERTIES_FILE), content, StandardOpenOption.CREATE);

		var properties = new IntegrationTestProperties(module1);

		assertThat(properties.getProperty(Config.DB_NAME)).isEqualTo("myname");
		assertThat(properties.getProperty(Config.DB_TARGET)).isEqualTo("nya-01");
		assertThat(properties.getProperty(Config.DB_USER)).isEqualTo("me");
		assertThat(properties.getProperty(Config.DB_PASSWD)).isEqualTo("secret");
	}

	@Test
	void shouldThrowOnMissingProperty(@TempDir Path dir) throws IOException {
		Files.createDirectories(dir.resolve(".mvn"));
		var module1 = dir.resolve("module1");
		Files.createDirectories(module1);

		var content = List.of("db.name=myname", "db.target=nya-01", "db.user=me");

		Files.write(dir.resolve(IntegrationTestProperties.PROPERTIES_FILE), content, StandardOpenOption.CREATE);

		assertThatThrownBy(() -> new IntegrationTestProperties(module1)).isInstanceOf(
				IntegrationTestProperties.IntegrationTestPropertiesException.class).hasMessageContaining("db.passwd");
	}

	@Test
	@Disabled("use env var")
	void shouldAcceptEnvironmentVariable() {
		// DB_NAME=myname DB_TARGET=nya-01 DB_USER=me DB_PASSWD=secret ./mvnw test -Dtest=IntegrationTestPropertiesTest#shouldAcceptEnvironmentVariable
		var properties = new IntegrationTestProperties();
		assertThat(properties.getProperty(Config.DB_NAME)).isEqualTo("myname");
		assertThat(properties.getProperty(Config.DB_TARGET)).isEqualTo("nya-01");
		assertThat(properties.getProperty(Config.DB_USER)).isEqualTo("me");
		assertThat(properties.getProperty(Config.DB_PASSWD)).isEqualTo("secret");
	}

	@Test
	@Disabled("use system properties")
	void shouldAcceptSystemProperty() {
		// ./mvnw test -Dtest=IntegrationTestPropertiesTest#shouldAcceptSystemProperty -Ddb.name=myname -Ddb.target=nya-01 -Ddb.user=me -Ddb.passwd=secret
		var properties = new IntegrationTestProperties();
		assertThat(properties.getProperty(Config.DB_NAME)).isEqualTo("myname");
		assertThat(properties.getProperty(Config.DB_TARGET)).isEqualTo("nya-01");
		assertThat(properties.getProperty(Config.DB_USER)).isEqualTo("me");
		assertThat(properties.getProperty(Config.DB_PASSWD)).isEqualTo("secret");
	}
}
