package creator;

import jakarta.json.bind.annotation.JsonbCreator;
import jakarta.json.bind.annotation.JsonbProperty;


public class SubClazz extends SuperClazz{

	private final String subName;

	@JsonbCreator
	public SubClazz(@JsonbProperty("name") String name, @JsonbProperty("subName") String subName) {
		super(name);
		this.subName = subName;
	}

	public String getSubName() {
		return subName;
	}

}
