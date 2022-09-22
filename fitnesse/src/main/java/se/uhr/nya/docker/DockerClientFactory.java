package se.uhr.nya.docker;

import java.time.Duration;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientConfig;
import com.github.dockerjava.core.DockerClientImpl;
import com.github.dockerjava.httpclient5.ApacheDockerHttpClient;
import com.github.dockerjava.transport.DockerHttpClient;

public class DockerClientFactory {

	private DockerClientFactory() {

	}

	private static class LazyHolder {

		static final DockerClientConfig dockerClientConfig = DefaultDockerClientConfig.createDefaultConfigBuilder().build();

		static final DockerHttpClient dockerHttpClient =
				new ApacheDockerHttpClient.Builder().dockerHost(dockerClientConfig.getDockerHost())
						.sslConfig(dockerClientConfig.getSSLConfig())
						.maxConnections(100)
						.connectionTimeout(Duration.ofSeconds(30))
						.responseTimeout(Duration.ofSeconds(45))
						.build();

		static final DockerClient INSTANCE = DockerClientImpl.getInstance(dockerClientConfig, dockerHttpClient);
	}

	public static DockerClient getInstance() {
		return LazyHolder.INSTANCE;
	}
}
