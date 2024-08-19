package test;

import javax.ws.rs.ext.ContextResolver;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;

//@Provider
//@Produces(MediaType.APPLICATION_JSON)
public class JacksonDatatypeJacksonProducer implements ContextResolver<ObjectMapper> {

	private final ObjectMapper json;

	public JacksonDatatypeJacksonProducer() throws Exception {
		this.json = JsonMapper.builder()
				.findAndAddModules()
				.build();
	}

	@Override
	public ObjectMapper getContext(Class<?> objectType) {
		return json;
	}
}