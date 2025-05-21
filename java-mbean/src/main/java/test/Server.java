package test;

import java.lang.management.ManagementFactory;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;

public class Server {

	public static void main(String[] args) {



		try {
			ObjectName objectName = new ObjectName("test:type=status,name=health");
			MBeanServer server = ManagementFactory.getPlatformMBeanServer();
			server.registerMBean(new Health(), objectName);
		} catch (MalformedObjectNameException | InstanceAlreadyExistsException | MBeanRegistrationException |
				NotCompliantMBeanException e) {
			e.printStackTrace();
		}

		System.out.println(Thread.currentThread().getName());

		while (true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
