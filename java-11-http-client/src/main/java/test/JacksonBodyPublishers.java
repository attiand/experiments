package test;

import java.io.UncheckedIOException;
import java.net.http.HttpRequest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonBodyPublishers {
    public static HttpRequest.BodyPublisher asJSON(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return HttpRequest.BodyPublishers.ofByteArray(mapper.writeValueAsBytes(object));
        } catch (JsonProcessingException e) {
            throw new UncheckedIOException(e);
        }
    }
}
