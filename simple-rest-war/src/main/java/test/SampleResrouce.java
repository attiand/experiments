package test;

import java.time.LocalDate;

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
public class SampleResrouce {

	private static final Logger LOG = LoggerFactory.getLogger(SampleResrouce.class);

	@GET
	public Response get() {
		var representation = new Representation();
		representation.setMessage("Hello");
		representation.setInterruptDate(LocalDate.now());
		return Response.ok(representation).build();
	}

	@PUT
	public Response put(Representation representation) {
		LOG.info("---- {}: {}", representation.getMessage(), representation.getInterruptDate());

		// { "20120310" }
		return Response.ok(representation.getInterruptDate()).build();
	}
}
