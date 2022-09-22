package se.uhr.nya.docker;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

import com.github.dockerjava.api.async.ResultCallbackTemplate;
import com.github.dockerjava.api.model.Frame;

public class WaitUntilContainerStartedResultCallback extends ResultCallbackTemplate<WaitUntilContainerStartedResultCallback, Frame> {

	private final Predicate<String> predicate;

	private final CountDownLatch latch = new CountDownLatch(1);

	public WaitUntilContainerStartedResultCallback(Predicate<String> predicate) {
		this.predicate = predicate;
	}

	@Override
	public void onNext(Frame frame) {
		if (frame != null) {
			String line = new String(frame.getPayload(), StandardCharsets.UTF_8);

			if (predicate.test(line)) {
				latch.countDown();
			}
		}
	}

	public boolean awaitContainerStarted(long timeout, TimeUnit timeUnit) throws InterruptedException {
		return latch.await(timeout, timeUnit);
	}
}