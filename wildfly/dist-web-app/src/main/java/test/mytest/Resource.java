package test.mytest;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import java.time.LocalDateTime;

@Path("/api")
@ApplicationScoped
public class Resource {

	@Inject
	MyProtoStreamSession session;

	@Path("/hello")
	@GET
	public String hello() {
		int current = session.getRequestCount();
		LocalDateTime prev = session.getTimestamp();
		current++;
		session.setRequestCount(current);
		session.setTimestamp(LocalDateTime.now());
		return "counter: " + current + " prev timestamp: " + prev;
	}
}
