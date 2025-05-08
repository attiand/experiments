import java.time.Instant;
import java.time.OffsetDateTime;
import org.junit.jupiter.api.Test;

import static java.time.temporal.ChronoUnit.HOURS;
import static org.assertj.core.api.Assertions.assertThat;

public class DateTest {

	@Test
	void shouldParseOpenApiDateTime() {
		var noOffset = OffsetDateTime.parse("2017-07-21T17:32:28Z");
		var withOffset = OffsetDateTime.parse("2017-07-21T17:32:28+00:00");

		assertThat(noOffset.toInstant()).isEqualTo(withOffset.toInstant());
		assertThat(noOffset.toLocalDateTime()).isEqualTo(withOffset.toLocalDateTime());
	}

	@Test
	void shouldParseInstant() {
		var noOffset = Instant.parse("2017-07-21T17:32:28Z");

		// instant parse with offset
		var withOffset = Instant.parse("2017-07-21T17:32:28+01:00");

		assertThat(noOffset).isEqualTo(withOffset.plus(1, HOURS));
		// but can't convert to LocalDateTime noOffset.toLocalDateTime()
	}
}
