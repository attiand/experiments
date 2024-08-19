package test;

import java.time.OffsetDateTime;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
@Path("/")
public class SampleResource {

	private static final Logger LOG = LoggerFactory.getLogger(SampleResource.class);

	@GET
	public Response getRepresentation() throws Exception {
		LOG.info("GET class");
		var representation = new SimpleRepresentation("Hello", OffsetDateTime.now());
		return Response.ok(representation).build();
	}

	@PUT
	public Response putRepresentation(SimpleRepresentation representation) throws Exception {
		LOG.info("PUT class");
		var tmp = new SimpleRepresentation(representation.getMessage(), representation.getTime().plusDays(1));
		return Response.ok(representation.getTime().plusDays(1)).build();
	}
}
