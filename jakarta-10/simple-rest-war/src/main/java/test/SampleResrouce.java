package test;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Path("/")
public class SampleResrouce {

	private static final Logger LOG = LoggerFactory.getLogger(SampleResrouce.class);

	@GET
	public Response get() {
		var representation = new Representation();
		LOG.info("hello");
		representation.setMessage("Hello");
		representation.setInterruptDate(LocalDate.now());
		return Response.ok(representation).build();
	}

	@PUT
	public Response put(Representation representation) {
		//OG.info("---- {}: {}", representation.getMessage(), representation.getInterruptDate());

		// { "20120310" }
		return Response.ok().build();
	}
}
