package test;

public class Health implements HealthMBean{

	@Override
	public String healthCheck() {
		return "hello " + Thread.currentThread().getName();
	}
}
