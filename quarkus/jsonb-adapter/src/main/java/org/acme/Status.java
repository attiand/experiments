package org.acme;

import java.util.Arrays;
import com.fasterxml.jackson.annotation.JsonProperty;

//@JsonbTypeAdapter(Status.Adapter.class)
public enum Status {
	@JsonProperty("ok")
	OK("ok"),
	@JsonProperty("error")
	ERROR("error");

	private final String value;

	private Status(String value) {
		this.value = value;
	}

	private static Status fromValue(String value) {
		return Arrays.stream(Status.values())
				.filter(s -> s.value.equals(value))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("Unknown value: " + value));
	}
/*
	public static class Adapter implements JsonbAdapter<Status, String> {

		@Override
		public String adaptToJson(Status status) throws Exception {
			return status.value;
		}

		@Override
		public Status adaptFromJson(String value) throws Exception {
			return Status.fromValue(value);
		}
	}
 */
}
