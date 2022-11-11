package test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.module.jakarta.xmlbind.JakartaXmlBindAnnotationModule;

import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.ext.ContextResolver;
import jakarta.ws.rs.ext.Provider;

@Provider
@Produces(MediaType.APPLICATION_JSON)
public class JaxbJsonObjectMapperProvider implements ContextResolver<ObjectMapper> {

	private static final JsonMapper MAPPER = JsonMapper.builder().addModule(new JakartaXmlBindAnnotationModule()).build();

	@Override
	public ObjectMapper getContext(Class<?> type) {
		return MAPPER;
	}
}
