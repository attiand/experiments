package test;

import java.util.Optional;

public record RecordOptionalReturn(String id, String name) {

	public Optional<String> getName() {
		return Optional.ofNullable(name);
	}
}
