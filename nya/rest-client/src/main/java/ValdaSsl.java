import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.apache.http.HttpStatus;

public class ValdaSsl {

	private static final MediaType APPLICATION_VND_UHR_NYA_V2_JSON = new MediaType("application", "vnd.uhr.nya.v2+json");
	private static final String PASSWD = "changeit";

	public static void main(String[] args)
		throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException, KeyManagementException {
		//System.setProperty("javax.net.debug", "all");

		//SSLContext sslContext = SSLContext.getInstance("TLSv1.2");
		//sslContext.init(null, null, null);

		Client client = ClientBuilder.newBuilder().keyStore(keyStore(), PASSWD).trustStore(trustStore()).build();

		URI uri = UriBuilder.fromUri("https://nya-11.its.umu.se:456/external/api/anmalningar/193202051219").build();

		//URI uri = UriBuilder.fromUri("https://nya-01.its.umu.se:455/valda/api/anmalningar/193202051219").build();

		WebTarget target = client.target(uri);
		try (Response response = target.request(APPLICATION_VND_UHR_NYA_V2_JSON).get()) {
			System.out.println("response status: " + response.getStatus());

			if (response.getStatus() == HttpStatus.SC_OK) {
				System.out.println(response.readEntity(String.class));
			}
		}
	}

	private static KeyStore keyStore() throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
		KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
		try (FileInputStream fin = new FileInputStream("keystore.jks")) {
			keyStore.load(fin, PASSWD.toCharArray());

		}
		return keyStore;
	}

	private static KeyStore trustStore() throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
		KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
		try (FileInputStream fin = new FileInputStream("cacerts")) {
			keyStore.load(fin, PASSWD.toCharArray());

		}
		return keyStore;
	}

}
