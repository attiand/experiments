package migrate;

import org.flywaydb.core.Flyway;

public record DatabaseMigrator(String message) {
	public static void main(String args[]) {
		Flyway flyway = Flyway.configure().configuration(System.getProperties()).dataSource("jdbc:postgresql://localhost:5432/postgres", "postgres", "mysecretpassword").load();
		flyway.migrate();
	}
}
