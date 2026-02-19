import org.gradle.api.GradleException
import org.gradle.api.DefaultTask
import org.gradle.api.provider.Property
import org.gradle.api.tasks.*
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.file.ProjectLayout
import javax.inject.Inject

import org.jfrog.artifactory.client.*

public abstract class Publish extends DefaultTask {

    @Input
    abstract Property<String> getVersion()

    @Input
    abstract Property<String> getUrl()

    @Input
    abstract Property<String> getUser()

    @Input
    abstract Property<String> getPassword()

    @Input
    abstract Property<String> getRepository()

    @InputFile
    public abstract RegularFileProperty getFile()

    private ProjectLayout projectLayout

    @Inject
    Publish(ProjectLayout projectLayout) {
        this.projectLayout = projectLayout
    }

    @TaskAction
    void publish() {
        logger.debug("publish ${file.get()}, version ${getVersion().get()}")

        Artifactory artifactory = ArtifactoryClientBuilder.create()
            .setUrl(getUrl().get())
            .setUsername(getUser().get())
            .setPassword(getPassword().get())
            .build()

        def projectRoot = projectLayout.settingsDirectory.asFile.toPath()

        def localPath = projectRoot.relativize(file.get().asFile.toPath()).toFile()

        def remotePath = new File(new File(localPath.getParentFile(), getVersion().get()), localPath.getName())

        try {
            def result = artifactory.repository(getRepository().get())
                .upload(remotePath.getPath(), file.get().getAsFile())
                .bySha1Checksum()
                .doUpload()

            logger.info("Published: ${file.get()}, download url: ${result.getDownloadUri()}")
        }
        catch (Exception e) {
            logger.error('Cant deploy to artifactory', e)

            throw new GradleException('Cant deploy to artifactory', e)
        }
    }
}