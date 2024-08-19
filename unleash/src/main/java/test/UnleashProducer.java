package test;

import java.net.URI;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import io.getunleash.DefaultUnleash;
import io.getunleash.Unleash;
import io.getunleash.util.UnleashConfig;


@ApplicationScoped
public class UnleashProducer {

	ManagedUnleashScheduledExecutor managedUnleashScheduledExecutor;

	private Unleash unleash;

	@Inject
	UnleashProducer(ManagedUnleashScheduledExecutor managedUnleashScheduledExecutor) {
		UnleashConfig config = UnleashConfig.builder()
				.appName("my-app")
				.instanceId("my-instance-1")
				.fetchTogglesInterval(1)
				.scheduledExecutor(managedUnleashScheduledExecutor)
				.unleashAPI(URI.create("http://nya-31.nya-srv.its.umu.se:8301/api"))
				.apiKey("*:development.efe5a56aed0e55d3186f9d3f92bc1d362011a76c0e29f6f6e0aa4692")
				.build();

		this.unleash = new DefaultUnleash(config);
	}

	@Produces
	public Unleash unleash() {
		return unleash;
	}
}
