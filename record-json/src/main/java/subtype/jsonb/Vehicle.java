package subtype.jsonb;

import jakarta.json.bind.annotation.JsonbSubtype;
import jakarta.json.bind.annotation.JsonbTypeInfo;

@JsonbTypeInfo(key = "objectType", value = { @JsonbSubtype(alias = "car", type = Car.class), @JsonbSubtype(alias = "truck", type = Truck.class) })
public interface Vehicle {

}
