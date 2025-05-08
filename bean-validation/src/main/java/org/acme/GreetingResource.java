package org.acme;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/hello")
public class GreetingResource {

    @GET
    public Response getPerson() {
        return Response.ok(new Person("John", "Doe", null)).build();
    }

    @PUT
    public Response updatePerson(@Valid Person person) {
        return Response.ok(person).build();
    }

    @Path("/param1/{i}")
    @GET
    public Response updatePerson(@Valid @Min(30) int i) {
        return Response.ok().build();
    }
}
