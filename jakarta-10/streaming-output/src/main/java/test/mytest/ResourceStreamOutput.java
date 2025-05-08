package test.mytest;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.StreamingOutput;

public class ResourceStreamOutput implements StreamingOutput {

	private final URL url;

	public ResourceStreamOutput(URL url) {
		this.url = url;
	}

	@Override
	public void write(OutputStream output) throws WebApplicationException {
		try(InputStream is = url.openStream()) {
			is.transferTo(output);
		} catch (IOException e) {
			throw new WebApplicationException("Can't transfer resource");
		}
	}
}
