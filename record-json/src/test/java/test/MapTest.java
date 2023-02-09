package test;

import java.util.HashMap;
import java.util.Map;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MapTest {

    private static Jsonb jsonb = JsonbBuilder.create();

    @Test
    void shouldSerlizeMap() {
        /*
        EasyRandomParameters parameters = new EasyRandomParameters()
                .objectFactory(new RecordFactory());
        EasyRandom easyRandom = new EasyRandom(parameters);

        var original = easyRandom.nextObject(Root.class);

         */

        var original = new Root();
        var key = new MyKey();
        key.setI(10);
        key.setName("Mattias");

        original.setMap(Map.of(key, "Andersson"));

        var tmp = toJson(original);
        System.out.println(tmp);
        var converted = fromJson(tmp, Root.class);

        System.out.println(converted);

        assertThat(converted).usingRecursiveComparison().isEqualTo(original);

    }

    public static <T> T fromJson(String json, Class<T> type) {
        return jsonb.fromJson(json, type);
    }

    public static <T> String toJson(T obj) {
        return jsonb.toJson(obj);
    }

}
