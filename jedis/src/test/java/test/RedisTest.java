package test;

import org.junit.jupiter.api.Test;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisConnectionException;

import java.util.Optional;

public class RedisTest {

    @Test
    void shouldConnect() {
        RedisClient client = new RedisClient("http://localhost:6379");

        Optional<String> value = client.get("test");

        System.out.println(value);

        client.put("test", "myvalue");

        Optional<String> value2 = client.get("test");

        System.out.println(value2);
    }
}
