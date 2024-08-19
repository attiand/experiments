package client;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import org.junit.jupiter.api.Test;

public class JaxrsClientTest {

	@Test
	void shouldCallNyaTools() throws IOException, InterruptedException {
		var map1 = new ConcurrentHashMap<String, String>();

		map1.put("one", "ONE");

		var map2 = new ConcurrentHashMap<String, String>();

		map2.put("one", "ONE");
		map2.put("two", "TWO");
		map2.put("tree", "TREE");

		map2.keySet().removeIf(e -> !map1.keySet().contains(e));

		System.out.println(map2);
	}
}
