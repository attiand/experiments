package test.mytest;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import jakarta.inject.Inject;
import jakarta.servlet.ServletContext;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api")
public class Resource {

	@Inject
	ServletContext servletContext;

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/hello")
	public String hello() {
		return "Hello";
	}

	@GET
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	@Path("/openapi")
	public Response openapi() {
		try {
			URL url = servletContext.getResource("/openapi/openapi.yaml");
			return Response.ok(new ResourceStreamOutput(url)).build();
		}
		catch (MalformedURLException  e) {
			throw new WebApplicationException("Can't get resource", e);
		}
	}
}
