package test;

public class SystemLogger {

	public static void main(String[] args) {
		System.getLogger("hello").log(System.Logger.Level.DEBUG, "Hello, world!");

	}
}
