package test;

import java.io.IOException;

import javax.management.JMX;
import javax.management.MBeanServerConnection;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

public class RemoteHealthCheckApp {

	public static void main(String[] args) throws IOException, MalformedObjectNameException {
		JMXServiceURL url =
				new JMXServiceURL("service:jmx:rmi:///jndi/rmi://:9999/jmxrmi");
		JMXConnector jmxc = JMXConnectorFactory.connect(url, null);

		MBeanServerConnection mbsc =
				jmxc.getMBeanServerConnection();

		ObjectName mbeanName = new ObjectName("test:type=status,name=health");
		HealthMBean mbeanProxy = JMX.newMBeanProxy(mbsc, mbeanName,
				HealthMBean.class, false);

		System.out.println(mbeanProxy.healthCheck());
	}
}
