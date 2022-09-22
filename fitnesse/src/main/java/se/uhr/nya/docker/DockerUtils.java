package se.uhr.nya.docker;

import java.util.List;
import java.util.Optional;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.Container;

public class DockerUtils {

	private static final DockerClient dockerClient = DockerClientFactory.getInstance();

	private DockerUtils() {

	}

	public static Optional<String> findContainer(String name) {
		return dockerClient.listContainersCmd()
				.withNameFilter(List.of(name))
				.withStatusFilter(List.of("running"))
				.withLimit(1)
				.exec()
				.stream()
				.map(Container::getId)
				.findFirst();
	}
}
