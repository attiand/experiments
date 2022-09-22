package jms;

import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
public class JMSSender {

	private static final Logger LOG = LoggerFactory.getLogger(SenderResource.class);

	@Resource
	private EJBContext ejbCtx;

	@Resource(name = JNDILocalNames.ELIGCR_QUEUE_CONNECTION_FACTORY)
	private QueueConnectionFactory eligCRQueueConnectionFactory;
	@Resource(name = JNDILocalNames.ELIGCR_QUEUE)
	private Queue eligCRQueue;

	public void sendMessage(int id) {

		try (QueueConnection con = eligCRQueueConnectionFactory.createQueueConnection();
				QueueSession session = con.createQueueSession(true, Session.AUTO_ACKNOWLEDGE);
				QueueSender sender = session.createSender(eligCRQueue)) {
			con.start();
			ObjectMessage objectMessage = session.createObjectMessage();

			// Send
			objectMessage.setObject(new MyMessage("hello", id));
			sender.send(objectMessage);
			session.commit();
		} catch (JMSException e) {
			ejbCtx.setRollbackOnly();
			LOG.error("sender exception", e);
		}
	}
}
