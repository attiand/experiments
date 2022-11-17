package mytest;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import se.uhr.nya.commons.jdbc.test.junit.DataSourceParameterResolver;
import se.uhr.nya.commons.jdbc.test.junit.DatabaseType;
import se.uhr.nya.commons.jdbc.test.junit.Table;

@Table("test")
class SimpleTest {

	@RegisterExtension
	static DataSourceParameterResolver dataSourceParameterResolver =
			DataSourceParameterResolver.builder().withDatabase(DatabaseType.DERBY).build();

	@Test
	void testName() throws Exception {
		assertThat(true).isTrue();
	}

}
