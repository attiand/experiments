package jms;

import javax.inject.Inject;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/message")
public class SenderResource {

	private static final Logger LOG = LoggerFactory.getLogger(SenderResource.class);

	@Inject
	JMSSender sender;

	@POST
	public void post(@DefaultValue("1") @QueryParam("number") int number) {
		for (int i = 0; i < number; i++) {
			LOG.info("send message");
			sender.sendMessage(i);
		}
	}

	@GET
	public String get() {
		LOG.info("get message");
		return "hello";
	}
}
