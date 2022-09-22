import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.apache.http.HttpStatus;

public class Jboss {

	public static void main(String[] args) {
		Client client = ClientBuilder.newBuilder().build();

		URI uri = UriBuilder.fromUri("http://download.jboss.org/jbosstools/photon/development/updates/content.xml").build();

		WebTarget target = client.target(uri);
		try (Response response = target.request(MediaType.APPLICATION_XML_TYPE).get()) {
			System.out.println("response status: " + response.getStatus());

			if (response.getStatus() == HttpStatus.SC_OK) {
				System.out.println(response.readEntity(String.class));
			}
		}
	}
}
