package clone;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;

public class DeepCloner {

	private static final Jsonb jsonb = JsonbBuilder.create();

	public <T> T deepClone(T object, Class<T> clazz) {
		return jsonb.fromJson(jsonb.toJson(object), clazz);
	}
}
