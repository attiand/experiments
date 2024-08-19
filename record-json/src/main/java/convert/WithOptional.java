package convert;

import java.util.Optional;

public record WithOptional(Optional<String> name) {

	public WithOptional(String name) {
		this(Optional.ofNullable(name));
	}

}
