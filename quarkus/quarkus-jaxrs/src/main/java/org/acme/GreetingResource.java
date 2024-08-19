package org.acme;

import java.util.UUID;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.Provider;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/message")
public class GreetingResource {

	@Inject
	MyBean myBean;

	@Context
	UriInfo uriInfo;

	@GET
	public Response hello() {
		myBean.foo();
		System.out.println(uriInfo.getAbsolutePath());
		return Response.ok().entity(new Message("Hello RESTEasy")).build();
	}

	@Path("/test/{id}")
	@GET
	public Response hello(@PathParam("id") UUID id) {
		return Response.ok().entity(new Message("Hello RESTEasy")).build();
	}

	@Path("/test/{id}/konto")
	@GET
	public Response konto(@Size(min = 2, max = 4) @PathParam("id") String id) {
		return Response.ok().entity(new Message("Hello RESTEasy")).build();
	}

	@PUT
	public Response message(@Valid Message message) {
		return Response.ok().entity(message).build();
	}
}
