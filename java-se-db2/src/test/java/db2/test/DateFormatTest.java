package db2.test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Properties;
import javax.sql.DataSource;
import com.ibm.db2.jcc.DB2SimpleDataSource;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class DateFormatTest {

	DataSource createDataSource() throws IOException {
		try (var reader = Files.newBufferedReader(Paths.get(System.getProperty("user.home"), ".gradle", "gradle.properties"))) {
			var properties = new Properties();
			properties.load(reader);

			var ds = new DB2SimpleDataSource();
			ds.setDriverType(4);
			ds.setServerName(properties.getProperty("host"));
			ds.setPortNumber(50_000);
			ds.setDatabaseName(properties.getProperty("db"));
			ds.setUser(properties.getProperty("user"));
			ds.setPassword(properties.getProperty("password"));
			return ds;
		}
	}

    @Disabled
	@Test
	void shouldInsertJavaTime() throws IOException, SQLException {
		var ds = createDataSource();

		var insert = """
				INSERT INTO TEST.TEST (time, date, timestamp)
					VALUES (?, ?, ?)
			""";

		try (var stmt = ds.getConnection().prepareStatement(insert)) {
			stmt.setObject(1, LocalTime.now());
			stmt.setObject(2, LocalDate.now());
			stmt.setObject(3, LocalDateTime.now());

			var res = stmt.execute();
			System.out.println(res);
		}
	}

    @Disabled
	@Test
	void shouldReadJavaTime() throws IOException, SQLException {
		var ds = createDataSource();

		var insert = """
				SELECT time, date, timestamp FROM TEST.TEST
			""";

		try (var stmt = ds.getConnection().prepareStatement(insert)) {
			try(ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					System.out.println(rs.getObject(1, LocalTime.class));
					System.out.println(rs.getObject(2, LocalDate.class));
					System.out.println(rs.getObject(3, LocalDateTime.class));
				}
			}
		}
	}
}
