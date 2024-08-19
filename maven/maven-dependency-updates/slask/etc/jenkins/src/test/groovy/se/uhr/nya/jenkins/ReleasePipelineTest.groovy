package se.uhr.nya.jenkins

import static com.lesfurets.jenkins.unit.MethodSignature.method
import static org.assertj.core.api.Assertions.*

import org.junit.Before
import org.junit.Test

import com.lesfurets.jenkins.unit.BasePipelineTest

import se.uhr.nya.jenkins.stubs.Manager
import se.uhr.nya.jenkins.stubs.Docker

public class ReleasePipelineTest extends BasePipelineTest {

	def commitId = '1768ddcc494fa97182b3c8fbc98bd34369d80cdb'

	def prepareDockerImageId = 'dcc39c18c457'
	def performDockerImageId = 'dcc39c18c458'


	@Override
	@Before
	void setUp() throws Exception {
		super.setUp()
		helper.registerAllowedMethod("timestamps", [Closure.class], null)
		helper.registerAllowedMethod("withSonarQubeEnv", [String.class, Closure.class], null)
		helper.registerAllowedMethod("checkout", [Map.class], {[GIT_COMMIT: commitId]})
		helper.registerAllowedMethod("ansiColor", [String.class, Closure.class], null)
		
		getBinding().setVariable('scm', [:])
		getBinding().setVariable('docker', new Docker())
		getBinding().setVariable('manager', new Manager())
		getBinding().setVariable('WORKSPACE', '.')
		getBinding().setVariable('params', [BRANCH: 'master'])		
		helper.registerAllowedMethod(method('load', String.class), { file ->
			return helper.runScript(new File(file).name, binding)
		})

		helper.registerAllowedMethod(method('readMavenPom', Map.class), { args ->
			return [version : '1.0']
		})
	}

	@Test
	void shouldRunSuccessScenario() throws Exception {
		helper.registerSharedLibrary(SharedLibraries.nya())

		runScript("release/Jenkinsfile")

		def deploy  = helper.callStack.findAll { call ->
			call.methodName == 'sh'
		}.collect { call ->
			call.callArgsToString(call)
		}.findAll{ call ->
			call.contains('release:perform')
		}

		assertThat(deploy.size()).isEqualTo(1)

		//printCallStack()

		assertJobStatusSuccess()
	}
}
