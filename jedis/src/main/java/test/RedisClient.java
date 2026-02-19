package test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Optional;

public class RedisClient {

    private final JedisPool jedisPool;

    public RedisClient(String url) {
        jedisPool = new JedisPool(url);
    }

    public boolean put(String key, String value) {
        try (Jedis jedis = jedisPool.getResource()) {
            String res = jedis.set(key, value);
            return res != null;
        }
    }

    public Optional<String> get(String key) {
        try (Jedis jedis = jedisPool.getResource()) {
            String value = jedis.get(key);
            return Optional.ofNullable(value);
        }
    }
}
