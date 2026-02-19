package org.control;

import io.vertx.mutiny.core.eventbus.EventBus;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;

@Dependent
public class EventService {

    @Inject
    EventBus bus;

    public void send(String message) {
        bus.send("myevent", message);
    }
}
