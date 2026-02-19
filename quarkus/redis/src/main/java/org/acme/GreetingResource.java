package org.acme;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/hello")
public class GreetingResource {

    @Inject
    GreetingService greetingService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response hello() {
        var res = greetingService.get("test");
        return Response.ok(res).build();
    }

    @PUT
    @Path("/{value}")
    public Response put(@PathParam("value")  String value) {
        greetingService.set("test", value);
        return Response.ok().build();
    }
}
