package test.mytest;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/api")
@ApplicationScoped
public class Resource {

	@Inject
	MySession session;

	@Path("/hello")
	@GET
	public String hello() {
		int current = session.getRequestCount();
		current++;
		session.setRequestCount(current);
		return "counter: " + current;
	}
}
