package se.uhr.nya.fitnesse;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

import java.io.IOException;
import java.io.InputStream;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

/**
* @ingroup Simulator
* @brief Administrera betalväxel simulator.
* 
* Förutsättningar:
* <pre>  
* |import|
* |se.uhr.nya.fitnesse.sim.bet|
* </pre>
*
* Exempel:
* <pre>
* |script|bet sim fixture                                                                                                  |
* |show  |connect                                                                                                          |
* |show  |för anrop till|/endpoint?param=1|svara med body|<?xml version="1.0" encoding="utf-8"?><PaymentInfo></PaymentInfo>|
* </pre>
*/

public class BetSimFixture {

	private static BetSimulator betSimulator;

	/**
	 * Anslut till Mock container, dvs. starta en ny om ingen finns eller anslut och återställ om det finns en befintlig. Om en ny startas
	 * kommer anropet blocka tills Docker containern startats. 
	 *
	 * <p>Exempel:</p>
	 * 
	 * <pre>
	 * |show|connect|
	 * </pre>
	 */

	public static String connect() {
		betSimulator = new BetSimulator();
		return betSimulator.getId();
	}

	/**
	 * Sätt förväntat beteende för specificerat anrop.
	 *
	 * <p>Exempel:</p>
	 * 
	 * <pre>
	 * |show |för anrop till|/endpoint?param=3|svara med body från fil|/lyckadbetalning1.xml|
	 * </pre>
	 */

	public void förAnropTillSvaraMedBodyFrånFil(String url, String file) throws IOException {
		try (InputStream is = BetSimFixture.class.getResourceAsStream(file)) {
			betSimulator.getMock()
					.register(get(urlEqualTo(url)).willReturn(
							aResponse().withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_XML).withBody(is.readAllBytes())));
		}
	}

	/**
	 * Sätt förväntat beteende för specificerat anrop.
	 *
	 * <p>Exempel:</p>
	 * 
	 * <pre>
	 * |show |för anrop till|/endpoint?param=3|svara med body|/lyckadbetalning1.xml|<?xml version="1.0" encoding="utf-8"?><PaymentInfo></PaymentInfo>|
	 * </pre>
	 */

	public void förAnropTillSvaraMedBody(String url, String body) {
		betSimulator.getMock()
				.register(get(urlEqualTo(url))
						.willReturn(aResponse().withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_XML).withBody(body)));
	}

	/**
	 * Sätt förväntat beteende för specificerat anrop.
	 *
	 * <p>Exempel:</p>
	 * 
	 * <pre>
	 * |show |för anrop till|/endpoint?param=3|svara med status|404|
	 * </pre>
	 */

	public void förAnropTillSvaraMedStatus(String url, int statusCode) {
		betSimulator.getMock().register(get(urlEqualTo(url)).willReturn(aResponse().withStatus(statusCode)));
	}

	/**
	 * Ta bort allt fördefinerat betende.
	 *
	 * <p>Exempel:</p>
	 * 
	 * <pre>
	 * |show |reset|
	 * </pre>
	 */

	public void reset() {
		betSimulator.reset();
	}

	/**
	 * Stänger ner simulatorn och dess Docker container. Detta behövs typiskt aldrig, kör reset för att rensa befintligt beteende.
	 *
	 * <p>Exempel:</p>
	 * 
	 * <pre>
	 * |show |close|
	 * </pre>
	 */

	public boolean close() {
		if (betSimulator != null) {
			betSimulator.close();
			return true;
		} else {
			return false;
		}
	}
}