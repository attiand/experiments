package test.mytest;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

@Produces("application/xml")
@Path("/jaxb")
public class JaxbResource {

	@GET
	@Path("/class")
	public Response getClassFromJAXB() {
		var body = new XmlClassRepresentation("type", "class");
		return Response.ok(body).build();
	}

	@GET
	@Path("/record")
	public Response getRecordFromJAXB() {
		var body = new XmlRecordRepresentation("type", "record");
		return Response.ok(body).build();
	}

}
