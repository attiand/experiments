package org.acme;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/exception")
public class ExceptionResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        throw new IllegalArgumentException("my exception");
    }
}
