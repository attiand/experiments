package unleash;

import java.net.URI;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import io.getunleash.DefaultUnleash;
import io.getunleash.Unleash;
import io.getunleash.util.UnleashConfig;
import io.getunleash.util.UnleashScheduledExecutorImpl;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

class ClientTest {

	//@RepeatedTest(20)
	@Test
	void shouldSubscribe() throws InterruptedException {

		var executor = new MyExecutor();

		UnleashConfig config = UnleashConfig.builder()
				.appName("my-app")
				.instanceId("my-instance-1")
				.fetchTogglesInterval(1)
				.scheduledExecutor(executor)
				.unleashAPI(URI.create("http://nya-01.nya-srv.its.umu.se:8301/api"))
				.apiKey("*:development.efe5a56aed0e55d3186f9d3f92bc1d362011a76c0e29f6f6e0aa4692")
				.build();

		Unleash unleash = new DefaultUnleash(config);

		System.out.println(unleash.isEnabled("mytoggle"));

		TimeUnit.SECONDS.sleep(	10);
	}


	static class MyExecutor extends UnleashScheduledExecutorImpl {

		@Override
		public ScheduledFuture setInterval(Runnable command, long initialDelaySec, long periodSec) {
			System.out.println("setInterval");
			return super.setInterval(command, initialDelaySec, periodSec);
		}

		@Override
		public Future<Void> scheduleOnce(Runnable runnable) {
			System.out.println("scheduleOnce");
			return super.scheduleOnce(runnable);
		}
	}
}
