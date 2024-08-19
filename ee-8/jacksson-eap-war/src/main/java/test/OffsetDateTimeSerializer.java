package test;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class OffsetDateTimeSerializer extends StdSerializer<OffsetDateTime> {

	public OffsetDateTimeSerializer() {
		this(null);
	}

	public OffsetDateTimeSerializer(Class<OffsetDateTime> t) {
		super(t);
	}

	@Override
	public void serialize(OffsetDateTime value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		gen.writeString(value.format(DateTimeFormatter.ISO_OFFSET_DATE));
	}
}
