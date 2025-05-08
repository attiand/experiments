package test;

import java.time.LocalDate;

import io.opentelemetry.api.metrics.LongCounter;
import io.opentelemetry.api.metrics.Meter;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/")
@ApplicationScoped
public class SampleResource {

	private static final Logger LOG = LoggerFactory.getLogger(SampleResource.class);

	private LongCounter counter;

	@Inject
	private Meter meter;

	@PostConstruct
	public void init() {
		counter = meter.counterBuilder("hello-metrics").setDescription("hello-metrics").setUnit("invocations").build();
	}

	@GET
	public Response get() {
		var representation = new Representation("Hello", LocalDate.now());
		LOG.info("hello");
		counter.add(1);
		return Response.ok(representation).build();
	}
}
