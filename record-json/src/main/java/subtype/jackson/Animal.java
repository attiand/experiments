package subtype.jackson;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "objectType")
@JsonSubTypes({ @Type(value = Cat.class, name = "cat"), @Type(value = Dog.class, name = "dog") })
public interface Animal {

}
