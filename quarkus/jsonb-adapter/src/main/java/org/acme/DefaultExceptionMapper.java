package org.acme;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
public class DefaultExceptionMapper implements ExceptionMapper<Throwable> {

	private static final Logger LOG = LoggerFactory.getLogger(DefaultExceptionMapper.class);

	@Override
	public Response toResponse(Throwable exception) {
		LOG.error(exception.getMessage(), exception);
		return Response.status(500).build();
	}
}
