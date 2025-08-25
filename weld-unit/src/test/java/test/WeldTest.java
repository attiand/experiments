package test;

import jakarta.inject.Inject;

import org.jboss.weld.junit5.auto.AddPackages;
import org.jboss.weld.junit5.auto.WeldJunit5AutoExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(WeldJunit5AutoExtension.class)
@AddPackages(MyService.class)
class WeldTest {

	@Inject
	private MyService myService;

	@Test
	void shouldInjectBean() {
		System.out.println(myService.myBean);
	}

	@Test
	void shouldInjectProducedBean() {
		System.out.println(myService.projo);
	}
}