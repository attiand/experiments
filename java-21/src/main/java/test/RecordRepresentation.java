package test;

public record RecordRepresentation(String message) {

	public RecordRepresentation() {
		this("default");
	}

	public static RecordRepresentation of(String message) {
		return new RecordRepresentation(message);
	}
}
