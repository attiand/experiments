package test;

import javax.annotation.processing.Generated;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AnnotationTest {

	@Test
	void shouldFindAnnotationOnType() {
		Class<MyRTO> myRTO = MyRTO.class;

		System.out.println(myRTO.getAnnotation(Generated.class));

		assertThat(myRTO.getAnnotation(Generated.class)).isNotNull();
	}
}
