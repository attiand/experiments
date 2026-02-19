package test;

import org.jfrog.artifactory.client.Artifactory;
import org.jfrog.artifactory.client.ArtifactoryClientBuilder;
import org.junit.jupiter.api.Test;

import java.io.File;

public class ArtifactoryTest {

	@Test
	void upload() {
        try(Artifactory artifactory = ArtifactoryClientBuilder.create()
                .setUrl("https://nya-artefact.its.umu.se")
                .setUsername("admin")
                .setPassword(System.getenv("ADMIN_PASSWORD"))
                .build()) {

            File file = new File("src/test/resources/bundle.zip");

            System.out.println(file.exists());

            var result = artifactory.repository("nya-api-spec")
                    .upload("slask2/test.zip", file)
                    .doUploadAndExplode();

            System.out.println(result);
        }
    }
}
