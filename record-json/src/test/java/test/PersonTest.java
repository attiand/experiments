package test;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.junit.jupiter.api.Test;

import java.util.UUID;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class PersonTest {
    private static Jsonb jsonb = JsonbBuilder.create();

    @Test
    void shouldConvertToJsonAndBack() {
        var original = Person.of("john", "doe", new Address("Maple street"));

        EasyRandomParameters parameters = new EasyRandomParameters()
                .objectFactory(new RecordFactory());
        EasyRandom easyRandom = new EasyRandom(parameters);

       // var original = easyRandom.nextObject(Person.class);

        var converted = fromJson(toJson(original), Person.class);

        assertThat(converted).usingRecursiveComparison().isEqualTo(original);
    }

    @Test
    void shouldNotCreateInvalidObject() {
        assertThatThrownBy(() -> {
            new Person(null, "doe", new Address("Maple street"));
        }).isInstanceOf(NullPointerException.class);
    }

    public static <T> T fromJson(String json, Class<T> type) {
        return jsonb.fromJson(json, type);
    }

    public static <T> String toJson(T obj) {
        return jsonb.toJson(obj);
    }
}