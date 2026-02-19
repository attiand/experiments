import org.gradle.api.GradleException
import org.gradle.api.DefaultTask
import org.gradle.api.provider.ListProperty
import org.gradle.api.tasks.*
import org.gradle.api.file.ProjectLayout
import org.gradle.api.file.ArchiveOperations
import org.gradle.api.file.FileSystemOperations
import javax.inject.Inject


public abstract class MyTest extends DefaultTask {

    private ProjectLayout projectLayout

    private ArchiveOperations archiveOperations

    private FileSystemOperations fs

    @Input
    public abstract ListProperty<String> getInclude()

    @Inject
    MyTest(ProjectLayout projectLayout, ArchiveOperations archiveOperations, FileSystemOperations fs) {
        this.projectLayout = projectLayout
        this.archiveOperations = archiveOperations
        this.fs = fs
    }

    @TaskAction
    void test() {
        logger.debug("test task")

        include.get().each { i ->
            logger.info("Included: ${i}")
        }

        def zipFile = project.layout.buildDirectory.file("test.zip").get().asFile
        def dest = archiveOperations.zipTree(zipFile)
        def sourceDir = project.file(".")

        // Use the project.copy method to pack files into the zip
        project.copy {
            from sourceDir
            // Use zipTree() to treat the destination file as a writable directory tree
            into dest
        }

        println("Successfully created ZIP file at: ${zipFile.absolutePath}")

/*
        def zipFile = projectLayout.buildDirectory.file("test.zip").get().asFile
        def sourceDir = project.file(".")
        def dest = archiveOperations.zipTree(zipFile)

        fs.copy {
            from sourceDir
            into dest
        }
        */
  
    }
}