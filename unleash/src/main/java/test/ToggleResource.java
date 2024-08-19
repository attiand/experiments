package test;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import io.getunleash.Unleash;

@Path("/toggle")
public class ToggleResource {

	@Inject
	Unleash unleash;

	@GET
	public String get() {
		return unleash.isEnabled("my-feature") ? "Enabled" : "Disabled";
	}
}
