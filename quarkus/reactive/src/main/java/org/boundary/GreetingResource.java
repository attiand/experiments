package org.boundary;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.control.EventService;
import org.control.GreetingService;

@Path("/hello")
public class GreetingResource {

    @Inject
    GreetingService greetingService;

    @Inject
    EventService eventService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response hello() {
        var res = greetingService.get("test");
        return Response.ok(res).build();
    }

    @PUT
    @Path("/{value}")
    public Response put(@PathParam("value")  String value) {
        eventService.send(value);
        return Response.ok().build();
    }
}
