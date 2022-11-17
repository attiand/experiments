package test.mytest;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponseBuilder;
import org.eclipse.microprofile.health.Liveness;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.enterprise.context.ApplicationScoped;

@Liveness
@ApplicationScoped
public class ValdaDatabaseHealthCheck implements HealthCheck {

	private static final Logger LOG = LoggerFactory.getLogger(ValdaDatabaseHealthCheck.class);

	@Override
	public HealthCheckResponse call() {
		HealthCheckResponseBuilder responseBuilder = HealthCheckResponse.named("valda-consumer-db-connection");

		try {
			LOG.info("up");

			responseBuilder.up();
		} catch (RuntimeException e) {
			responseBuilder.down().withData("error", e.getMessage());
		}

		return responseBuilder.build();
	}
}
