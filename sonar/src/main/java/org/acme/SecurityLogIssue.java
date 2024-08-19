package org.acme;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;

public class SecurityLogIssue {

	private static final Logger LOG = LoggerFactory.getLogger(SecurityLogIssue.class);

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("student/skyddsanteckning/{studentId}")
	public Response sparaSkyddsanteckning(@PathParam("studentId") String studentIdString, String skyddsanteckning) {

		// javasecurity:S5145
		if (LOG.isDebugEnabled()) {
			LOG.debug("PPV spara skyddsanteckning för student {} med skyddsanteckning {}", cleanString(skyddsanteckning), cleanString(studentIdString));
		}

		return Response.ok().build();
	}

	String cleanString(String input) {
		return input.replaceAll("[\n\r]", "_");
	}

}
