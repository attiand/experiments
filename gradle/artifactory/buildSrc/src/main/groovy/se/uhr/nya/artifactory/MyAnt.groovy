import org.gradle.api.DefaultTask
import org.gradle.api.tasks.*

public abstract class MyAnt extends DefaultTask {

    @TaskAction
    void test() {
        getAnt().echo(message: "Hello from ant!")
    }
}