package org.acme;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/person")
public class PersonResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public PersonDTO pojo() {
		return new PersonDTO("John", "Doe", Status.OK);
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public PersonDTO put(PersonDTO dto) {
		return dto;
	}

}
