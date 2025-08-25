package test;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

@ApplicationScoped
public class MyProducer {

	@Produces
	@ApplicationScoped
	@MyDataSource
	MyPojo createMyPojo() {
		return new MyPojo("Hello, World!");
	}

}
