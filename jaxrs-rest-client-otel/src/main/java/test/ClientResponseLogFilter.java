package test;

import java.io.IOException;
import jakarta.ws.rs.client.ClientRequestContext;
import jakarta.ws.rs.client.ClientRequestFilter;
import jakarta.ws.rs.client.ClientResponseContext;
import jakarta.ws.rs.client.ClientResponseFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientResponseLogFilter implements ClientRequestFilter, ClientResponseFilter {

	private static final Logger LOG = LoggerFactory.getLogger("http.client");

	@Override
	public void filter(ClientRequestContext requestContext) throws IOException {
		if(LOG.isTraceEnabled()) {
			LOG.trace("Request: {}: {}: {}", requestContext.getMethod(), requestContext.getUri(), requestContext.getHeaders());
		}
		else {
			LOG.debug("Request: {} {}", requestContext.getMethod(), requestContext.getUri());
		}
	}

	@Override
	public void filter(ClientRequestContext requestContext, ClientResponseContext responseContext) throws IOException {
		if(LOG.isTraceEnabled()) {
			LOG.trace("Response: {}: {}: {}", responseContext.getStatus(), responseContext.getStatusInfo(), responseContext.getHeaders());
		}
		else {
			LOG.debug("Response: {}: {}", responseContext.getStatus(), responseContext.getStatusInfo());
		}
	}
}
