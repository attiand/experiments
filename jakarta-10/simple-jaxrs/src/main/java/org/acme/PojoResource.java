package org.acme;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/pojo")
public class PojoResource {

    public static record MyDto(String firstName, String lastName){

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public MyDto pojo() {
        return new MyDto("Mattias", "Andersson");
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public MyDto pojo(@PathParam("id") boolean error) {
        if(error) {
            throw new IllegalArgumentException("My exception");
        }
        return new MyDto("Mattias", "Andersson");
    }
}
