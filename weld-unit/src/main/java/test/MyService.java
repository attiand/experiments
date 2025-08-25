package test;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;

@Dependent
public class MyService {

	@Inject
	MyBean myBean;

	@Inject
	@MyDataSource
	MyPojo projo;

}
