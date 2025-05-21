package test;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.management.JMX;
import javax.management.MBeanServerConnection;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;

public class LocalHealthCheckApp {

	public static void main(String[] args) throws IOException, MalformedObjectNameException, AttachNotSupportedException {
		List<VirtualMachineDescriptor> vms = VirtualMachine.list();

		VirtualMachineDescriptor desc = vms.stream()
				.filter(d -> "test.Server".equals(d.displayName()))
				.findFirst()
				.orElseThrow(() -> new RuntimeException("No matching VM found"));

		VirtualMachine vm = VirtualMachine.attach(desc);
		Properties props = vm.getAgentProperties();
		String connectorAddress = props.getProperty("com.sun.management.jmxremote.localConnectorAddress");
		if (connectorAddress == null) {
			connectorAddress = vm.startLocalManagementAgent();
		}

		System.out.println(connectorAddress);

		JMXServiceURL url = new JMXServiceURL(connectorAddress);

		try (JMXConnector connector = JMXConnectorFactory.connect(url)) {
			MBeanServerConnection mbeanConn = connector.getMBeanServerConnection();
			ObjectName mbeanName = new ObjectName("test:type=status,name=health");
			HealthMBean mbeanProxy = JMX.newMBeanProxy(mbeanConn, mbeanName, HealthMBean.class, false);

			System.out.println(mbeanProxy.healthCheck());
		}
	}
}