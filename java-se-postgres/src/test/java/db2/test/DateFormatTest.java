package db2.test;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import javax.sql.DataSource;
import org.junit.jupiter.api.Test;
import org.postgresql.ds.PGSimpleDataSource;

public class DateFormatTest {

	DataSource createDataSource() throws IOException {
		PGSimpleDataSource ds = new PGSimpleDataSource() ;
		ds.setDatabaseName( "postgres" );
		ds.setUser( "postgres" );
		ds.setPassword( "mysecretpassword" );
		return ds;
	}

	@Test
	void shouldInsertJavaTime() throws IOException, SQLException {
		var ds = createDataSource();

		var insert = """
				INSERT INTO TEST.TEST (time, date, timestamp, time_tz, timestamp_tz)
					VALUES (?, ?, ?, ?, ?)
			""";

		try (var stmt = ds.getConnection().prepareStatement(insert)) {
			stmt.setObject(1, LocalTime.now());
			stmt.setObject(2, LocalDate.now());
			stmt.setObject(3, LocalDateTime.now());
			stmt.setObject(4, OffsetTime.now());
			stmt.setObject(5, OffsetDateTime.now());

			var res = stmt.execute();
			System.out.println(res);
		}
	}

	@Test
	void shouldReadJavaTime() throws IOException, SQLException {
		var ds = createDataSource();

		var insert = """
				SELECT time, date, timestamp, time_tz, timestamp_tz FROM TEST.TEST
			""";

		try (var stmt = ds.getConnection().prepareStatement(insert)) {
			try(ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					System.out.println(rs.getObject(1, LocalTime.class));
					System.out.println(rs.getObject(2, LocalDate.class));
					System.out.println(rs.getObject(3, LocalDateTime.class));
					var tof = rs.getObject(4, OffsetTime.class);
					System.out.println(tof.getOffset());
					System.out.println(tof);
					var ts = rs.getObject(5, OffsetDateTime.class);
					System.out.println(ts);
					System.out.println(ts.getOffset());
					var of = ts.format( java.time.format.DateTimeFormatter.ISO_OFFSET_DATE_TIME );
					System.out.println(of);
				}
			}
		}
	}
}
