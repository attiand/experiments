package test;

import java.util.stream.Collectors;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class ByteArrayTest {
    private static Jsonb jsonb = JsonbBuilder.create();

    @Test
    void shouldConvertToJsonAndBack() {
/*
        EasyRandom easyRandom = new EasyRandom(new EasyRandomParameters().randomize());

        System.out.println(original);

        var converted = fromJson(toJson(original), ByteArray.class);

        assertThat(converted).usingRecursiveComparison().isEqualTo(original);
 */
    }
}
