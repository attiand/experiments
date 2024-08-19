package org.acme;

import java.util.List;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.EntityPart;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("multipart")
@RegisterRestClient(baseUri = "http://localhost:8080")
public interface MultipartService {

	@Path("v1")
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response postFileV1(List<EntityPart> parts);
}
