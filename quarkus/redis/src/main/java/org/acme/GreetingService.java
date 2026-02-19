package org.acme;

import io.quarkus.redis.datasource.RedisDataSource;
import io.quarkus.redis.datasource.value.ValueCommands;
import io.vertx.redis.client.impl.types.SimpleStringType;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;

@Dependent
public class GreetingService {

    private final RedisDataSource redisDS;
    private final ValueCommands<String, String> valueCommands;

    @Inject
    public GreetingService(RedisDataSource redisDS) {
        this.redisDS = redisDS;
        this.valueCommands = redisDS.value(String.class);
    }

    public void set(String key, String value) {
        var res = redisDS.execute("SET", key, value);
        System.out.println(res.toString());
        System.out.println(res.toString().equals("OK"));
        //valueCommands.set(key, value);
    }

    public String get(String key) {
        return valueCommands.get(key);
    }
}
