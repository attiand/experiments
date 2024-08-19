import java.time.Duration;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class VirtualThreadTest {

	private final Logger LOG = LoggerFactory.getLogger(VirtualThreadTest.class);

	@Test
	void pinTest() throws InterruptedException {
		Thread thread = Thread.ofVirtual().start(() -> {
			this.sync();
			System.out.println("Hello");
		});
		thread.join();
	}

	synchronized void sync() {

		LOG.atInfo().log("Thread: {}", Thread.currentThread());
		for(int i = 0; i < 200_000; i++) {
			//System.out.println(i);
		}
	}

	private void sleep() {
		try {
			Thread.sleep(Duration.ofSeconds(1));
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}
