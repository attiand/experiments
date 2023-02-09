package org.acme;

import java.net.URI;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

@Path("/hello")
public class GreetingResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Product get() {
        var prod = new Product();
        prod.setName("legacy");
        prod.setVersion("master");
        return prod;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void put(Product product) {
        System.out.println(product.getName());
        System.out.println(product.getVersion());
    }
}