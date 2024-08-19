package test;

import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledFuture;
import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedScheduledExecutorService;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import io.getunleash.util.UnleashScheduledExecutor;

@Dependent
class ManagedUnleashScheduledExecutor implements UnleashScheduledExecutor {

	@Resource
	ManagedScheduledExecutorService managedScheduledExecutorService;

	@Override
	public ScheduledFuture<?> setInterval(Runnable command, long initialDelaySec, long periodSec) throws RejectedExecutionException {
		System.out.println("Scheduling once");
		return managedScheduledExecutorService.scheduleAtFixedRate(command, initialDelaySec, periodSec, java.util.concurrent.TimeUnit.SECONDS);
	}

	@Override
	public Future<Void> scheduleOnce(Runnable runnable) {
		System.out.println("Scheduling once");
		return (Future<Void>) managedScheduledExecutorService.submit(runnable);
	}

	/*
	@Override
	public void shutdown() {
		managedScheduledExecutorService.shutdown();

	}
	 */
}
