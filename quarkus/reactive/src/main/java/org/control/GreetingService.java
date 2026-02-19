package org.control;

import io.quarkus.redis.datasource.RedisDataSource;
import io.quarkus.redis.datasource.value.ValueCommands;
import io.quarkus.vertx.ConsumeEvent;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;

@Dependent
public class GreetingService {

    private final ValueCommands<String, String> valueCommands;
    //private final TestDAO testDAO;

    @Inject
    public GreetingService(RedisDataSource redisDS) {
        this.valueCommands = redisDS.value(String.class);
        //this.testDAO = testDAO;
    }

    @ConsumeEvent(value = "myevent", blocking = true)
    //@ConsumeEvent("myevent")
    public void set(String value) {
        valueCommands.set("test", value);
        //testDAO.set(value);
    }

    public String get(String key) {
        return valueCommands.get(key);
    }
}
