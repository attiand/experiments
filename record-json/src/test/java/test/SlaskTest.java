package test;

import org.junit.jupiter.api.Test;

public class SlaskTest {

    @Test
    void shouldTestRecord() {
        var test = new MyTest(true);
        System.out.println(test.hasChangd());
    }
}
