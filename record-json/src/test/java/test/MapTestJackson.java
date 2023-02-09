package test;

import java.util.Map;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MapTestJackson {

    private static Jsonb jsonb = JsonbBuilder.create();
    private static ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void shouldSerlizeMap() throws JsonProcessingException {
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

        var tmp =   objectMapper.writeValueAsString(original);
        System.out.println(tmp);
        var converted = objectMapper.readValue(tmp, Root.class);

        System.out.println(converted);

       // assertThat(converted).usingRecursiveComparison().isEqualTo(original);

    }

    public static <T> T fromJson(String json, Class<T> type) {
        return jsonb.fromJson(json, type);
    }

    public static <T> String toJson(T obj) {
        return jsonb.toJson(obj);
    }

}
