package test;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.io.InputStream;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.function.Supplier;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonBodyHandlers {

    public static <W> HttpResponse.BodyHandler<Supplier<W>> asJSON(Class<W> targetType) {
        return (responseInfo) -> BodySubscribers.asJSON(targetType);
    }

    public static <W> HttpResponse.BodyHandler<Supplier<List<W>>> asJSONList(Class<W> targetType) {
        return (responseInfo) -> BodySubscribers.asJSONList(targetType);
    }

    static class BodySubscribers {
        public static <W> HttpResponse.BodySubscriber<Supplier<W>> asJSON(Class<W> targetType) {
            HttpResponse.BodySubscriber<InputStream> upstream = HttpResponse.BodySubscribers.ofInputStream();

            HttpResponse.BodySubscriber<Supplier<W>> downstream = HttpResponse.BodySubscribers.mapping(
                    upstream,
                    (InputStream is) -> () -> {
                        try (InputStream stream = is) {
                            ObjectMapper objectMapper = new ObjectMapper();
                            return objectMapper.readValue(stream, targetType);
                        } catch (IOException e) {
                            throw new UncheckedIOException(e);
                        }
                    });
            return downstream;
        }

        public static <W> HttpResponse.BodySubscriber<Supplier<List<W>>> asJSONList(Class<W> targetType) {
            HttpResponse.BodySubscriber<InputStream> upstream = HttpResponse.BodySubscribers.ofInputStream();

            HttpResponse.BodySubscriber<Supplier<List<W>>> downstream = HttpResponse.BodySubscribers.mapping(
                    upstream,
                    (InputStream is) -> () -> {
                        try (InputStream stream = is) {
                            ObjectMapper objectMapper = new ObjectMapper();
                            return objectMapper.readerForListOf(targetType).readValue(stream);
                        } catch (IOException e) {
                            throw new UncheckedIOException(e);
                        }
                    });
            return downstream;
        }
    }
}
