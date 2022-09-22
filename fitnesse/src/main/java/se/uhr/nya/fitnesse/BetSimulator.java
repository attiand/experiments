package se.uhr.nya.fitnesse;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicMarkableReference;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.api.model.HostConfig;
import com.github.dockerjava.api.model.Ports;
import com.github.dockerjava.api.model.Ports.Binding;
import com.github.tomakehurst.wiremock.client.WireMock;

import se.uhr.nya.docker.DockerUtils;
import se.uhr.nya.docker.DockerClientFactory;
import se.uhr.nya.docker.WaitUntilContainerStartedResultCallback;

public class BetSimulator {

	private static final String MOCK_HOST = "localhost";

	private static final String MOCK_IMAGE_NAME = "wiremock/wiremock";

	private static final String MOCK_IMAGE_VERSION = "2.32.0";

	private static final String BETSIM_CONTAINER_NAME = "sim-bet";

	private static final int BETSIM_PORT = 8090;

	private static final Pattern WIREMOCK_STARTED_LOG_STMT = Pattern.compile("verbose:\\s*false");

	private static final Logger LOG = LoggerFactory.getLogger(BetSimulator.class);

	private static final DockerClient dockerClient = DockerClientFactory.getInstance();

	private final String containerId;

	private final AtomicMarkableReference<WireMock> mock;

	public BetSimulator() {
		Optional<String> existingId = DockerUtils.findContainer(BETSIM_CONTAINER_NAME);

		containerId = existingId.map(id -> {
			LOG.info("Found existing {} container with id {}", BETSIM_CONTAINER_NAME, id);
			return id;
		}).orElseGet(BetSimulator::createAndStartContainer);

		var wm = new WireMock(MOCK_HOST, BETSIM_PORT);

		mock = new AtomicMarkableReference<>(wm, true);

		if (existingId.isPresent()) {
			wm.resetMappings();
		}
	}

	public String getId() {
		return containerId;
	}

	public WireMock getMock() {
		return mock.getReference();
	}

	public void reset() {
		mock.getReference().resetMappings();
	}

	public void close() {
		if (mock.isMarked()) {
			mock.getReference();
			mock.set(null, false);
		}
	}

	private static String createAndStartContainer() {
		dockerClient.pullImageCmd(MOCK_IMAGE_NAME).withTag(MOCK_IMAGE_VERSION).start();

		HostConfig hostConfig = HostConfig.newHostConfig()
				.withAutoRemove(true)
				.withPortBindings(new Ports(new ExposedPort(8080), Binding.bindPort(8090)));

		try (var cmd = dockerClient.createContainerCmd(MOCK_IMAGE_NAME + ":" + MOCK_IMAGE_VERSION)
				.withName(BETSIM_CONTAINER_NAME)
				.withHostConfig(hostConfig)
				.withLabels(Map.of("type", "test"))) {

			var container = cmd.exec();

			dockerClient.startContainerCmd(container.getId()).exec();

			LOG.info("Started {} container with id {}", BETSIM_CONTAINER_NAME, container.getId());

			WaitUntilContainerStartedResultCallback callback =
					new WaitUntilContainerStartedResultCallback(WIREMOCK_STARTED_LOG_STMT.asPredicate());

			dockerClient.logContainerCmd(container.getId())
					.withFollowStream(true)
					.withSince(0)
					.withStdOut(true)
					.withStdErr(true)
					.exec(callback);

			try {
				if (!callback.awaitContainerStarted(20L, TimeUnit.SECONDS)) {
					throw new RuntimeException(BETSIM_CONTAINER_NAME + " dit not start during the stipulated time");
				}
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				throw new RuntimeException(e);
			}

			return container.getId();
		}
	}
}
