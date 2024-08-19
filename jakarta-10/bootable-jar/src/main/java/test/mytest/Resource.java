package test.mytest;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.EntityPart;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api")
public class Resource {

	@GET
	@Path("/hello")
	public String hello() {
		return "Hello RESTEasy";
	}

	@Path("v1")
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response postWidget1(List<EntityPart> parts) throws IOException {
		for(EntityPart part : parts) {
			System.out.println(part.getName());
			String content = new String(part.getContent().readAllBytes(), StandardCharsets.UTF_8);
			System.out.println(content);
		}

		return Response.ok().build();
	}

	@Path("v2")
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response postWidget2(@FormParam("data") String data) throws IOException {
		System.out.println("postWidget2");
		System.out.println(data);
		return Response.ok().build();
	}
}
