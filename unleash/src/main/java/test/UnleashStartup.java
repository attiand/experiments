package test;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import io.getunleash.Unleash;

@Startup
@Singleton
public class UnleashStartup {

	@Inject
	public Unleash unleash;

	@PreDestroy
	public void shutdown() {
		unleash.shutdown();
	}
}
