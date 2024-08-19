package org.acme;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.EntityPart;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("multipart")
public class MultiPartResource {

	@Path("v1")
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response postFileV1(List<EntityPart> parts) throws IOException {
		for(EntityPart part : parts) {
			System.out.println(part.getName());
			String content = new String(part.getContent().readAllBytes(), StandardCharsets.UTF_8);
			System.out.println(content);
		}

		return Response.ok().build();
	}

	// Works with quarkus-rest (reactive)
	@Path("v2")
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response postFileV2(@FormParam("data") InputStream data) throws IOException {
		System.out.println(data);

		String content = new String(data.readAllBytes(), StandardCharsets.UTF_8);
		System.out.println(content);

		return Response.ok().build();
	}

	// does not deploy with quarkus-rest (reactive)
	/*
	@Path("v3")
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response postFileV3(@FormParam("data") EntityPart data) throws IOException {
		System.out.println(data);

		String content = new String(data.getContent().readAllBytes(), StandardCharsets.UTF_8);
		System.out.println(content);

		return Response.ok().build();
	}
	 */

}
