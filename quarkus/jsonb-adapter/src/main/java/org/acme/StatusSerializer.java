package org.acme;

import java.lang.reflect.Type;
import java.util.Arrays;
import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonParser;
/*
@JsonbTypeSerializer(StatusSerializer.Serializer.class)
@JsonbTypeDeserializer(StatusSerializer.Deserializer.class)
public enum StatusSerializer {
	OK("ok"),
	ERROR("error");

	private final String value;

	private StatusSerializer(String value) {
		this.value = value;
	}

	public static class Serializer implements JsonbSerializer<StatusSerializer> {
		@Override
		public void serialize(StatusSerializer status, JsonGenerator generator, SerializationContext ctx) {
			generator.write(status.value);
		}
	}

	public static class Deserializer implements JsonbDeserializer<StatusSerializer> {
		@Override
		public StatusSerializer deserialize(JsonParser parser, DeserializationContext ctx, Type rtType) {
			return Arrays.stream(StatusSerializer.values())
					.filter(status -> status.value.equals(parser.getString()))
					.findFirst()
					.orElse(null);
		}
	}
}
*/