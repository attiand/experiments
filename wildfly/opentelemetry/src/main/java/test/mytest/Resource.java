package test.mytest;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/api")
public class Resource {

	@GET
	@Path("/hello")
	public String hello() {
		return "Hello RESTEasy";
	}
}
