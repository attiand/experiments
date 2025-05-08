package test;

import java.util.Set;
import ch.qos.logback.classic.spi.ThrowableProxy;

public class Main {
	public static void main(String[] args) {
		System.out.println("Hello World!");
		new ThrowableProxy(new Exception("test"));
	}
}
