package test;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.model.Bind;
import com.github.dockerjava.api.model.PortBinding;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.api.model.Container;

import org.junit.jupiter.api.Test;

import java.util.List;

public class TrivyTest {

    @Test
    void shouldRunTrivy() {
        DefaultDockerClientConfig.Builder config
                = DefaultDockerClientConfig.createDefaultConfigBuilder();
        DockerClient dockerClient = DockerClientBuilder
                .getInstance(config)
                .build();


        List<Container> containers = dockerClient.listContainersCmd().exec();

        containers.forEach(container -> {
            System.out.println(container.getId());
        });

        CreateContainerResponse container
                = dockerClient.createContainerCmd("mongo:3.6")
                .withCmd("--bind_ip_all")
                .withName("mongo")
                .withHostName("baeldung")
                .withEnv("MONGO_LATEST_VERSION=3.6")
                .withPortBindings(PortBinding.parse("9999:27017"))
                .withBinds(Bind.parse("/Users/baeldung/mongo/data/db:/data/db")).exec();

        container.


    }
}
