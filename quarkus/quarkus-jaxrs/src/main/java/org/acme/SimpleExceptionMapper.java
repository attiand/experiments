package org.acme;

import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
public class SimpleExceptionMapper implements ExceptionMapper<NotFoundException> {

	private static final Logger LOG = LoggerFactory.getLogger(SimpleExceptionMapper.class);

	@Override
	public Response toResponse(NotFoundException exception) {
		LOG.info("---- mapper -----");

		LOG.error(exception.getMessage(), exception);
		return Response.status(500).build();
	}
}

