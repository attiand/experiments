package db2.test;

import com.ibm.db2.jcc.DB2SimpleDataSource;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Properties;

public class ConnectTest {

    @Test
    void sholdConnect() throws IOException, SQLException {
        try (var reader = Files.newBufferedReader(Paths.get(System.getProperty("user.home"), ".gradle", "gradle.properties"))) {
            var properties = new Properties();
            properties.load(reader);

            var ds = new DB2SimpleDataSource();
            ds.setDriverType(4);
            ds.setServerName(properties.getProperty("host"));
            ds.setPortNumber(34591);
            ds.setDatabaseName(properties.getProperty("db"));
            ds.setUser(properties.getProperty("user"));
            ds.setPassword(properties.getProperty("password"));

            var insert = """
				SELECT CURRENT SERVER FROM SYSIBM.SYSDUMMY1;
			""";

            try (var stmt = ds.getConnection().prepareStatement(insert)) {
                try(ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        System.out.println(rs.getString(1));
                    }
                }
            }
        }
    }
}
