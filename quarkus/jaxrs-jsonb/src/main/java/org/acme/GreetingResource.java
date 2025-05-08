package org.acme;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/hello")
public class GreetingResource {

    @Inject
    ServletContext servletContext;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from Quarkus REST";
    }


    @GET
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    @Path("/openapi")
    public Response openapi() {
        try {
            URL url = servletContext.getResource("/openapi/openapi.yaml");
            System.out.println("-----> " + url);
            return Response.ok(new ResourceStreamOutput(url.toURI())).build();
        }
        catch (MalformedURLException | URISyntaxException e) {
            throw new WebApplicationException("Can't get resource", e);
        }
    }
}
