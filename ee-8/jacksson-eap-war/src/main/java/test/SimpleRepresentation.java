package test;

import java.time.OffsetDateTime;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class SimpleRepresentation {

	@JsonProperty("mymessage")
	private String message;

	@JsonSerialize(using = OffsetDateTimeSerializer.class)
	private OffsetDateTime time;

	public SimpleRepresentation() {
	}

	public SimpleRepresentation(String message, OffsetDateTime time) {
		this.message = message;
		this.time = time;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public OffsetDateTime getTime() {
		return time;
	}

	public void setTime(OffsetDateTime time) {
		this.time = time;
	}
}
