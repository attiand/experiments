package test;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.spi.Bean;
import jakarta.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;

import org.jboss.weld.junit.MockBean;
import org.jboss.weld.junit5.WeldInitiator;
import org.jboss.weld.junit5.WeldJunit5Extension;
import org.jboss.weld.junit5.WeldSetup;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

class Foo {
	@Inject
	MyInterface bar;
}

@ExtendWith(WeldJunit5Extension.class)
class WeldMockTest {

	@WeldSetup
	public WeldInitiator weld = WeldInitiator.from(Foo.class).addBeans(createBarBean()).build();

	static Bean<?> createBarBean() {
		return MockBean.builder().types(MyInterface.class).scope(ApplicationScoped.class).creating(MyInterface.class).build();
	}

	@Test
	void testFoo() {
		assertThat(weld.select(Foo.class).get().bar).isInstanceOf(MyInterface.class);
	}
}