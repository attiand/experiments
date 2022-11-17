package se.uhr.nya.jenkins

import static com.lesfurets.jenkins.unit.MethodSignature.method
import static org.assertj.core.api.Assertions.*

import org.junit.Before
import org.junit.Test

import se.uhr.nya.jenkins.stubs.Manager
import se.uhr.nya.jenkins.stubs.Docker

import com.lesfurets.jenkins.unit.BasePipelineTest

public class IntegrationPipelineTest extends BasePipelineTest {

	def commitId = '1768ddcc494fa97182b3c8fbc98bd34369d80cdb'

	@Override
	@Before
	void setUp() throws Exception {
		super.setUp()
		helper.registerAllowedMethod("timestamps", [Closure.class], null)
		helper.registerAllowedMethod("withSonarQubeEnv", [String.class, Closure.class], null)
		helper.registerAllowedMethod("dependencyCheckPublisher", [Map.class], null)
		helper.registerAllowedMethod("ansiColor", [String.class, Closure.class], null)
		helper.registerAllowedMethod("writeFile", [Map.class], null)
		
		getBinding().setVariable('scm', [:])
		getBinding().setVariable('docker', new Docker())
		getBinding().setVariable('manager', new Manager())
		helper.registerAllowedMethod(method('load', String.class), { file ->
			return helper.runScript(new File(file).name, binding)
		})

		helper.registerAllowedMethod(method('readMavenPom', Map.class), { args ->
			return [version : '1.0-SNAPSHOT']
		})

		def params = [:]

		params['GERRIT_BRANCH'] = 'master'
		params['GERRIT_PATCHSET_REVISION'] = commitId

		binding.setVariable('params', params)
	}

	@Test
	void shouldRunSuccessScenario() throws Exception {
		helper.registerSharedLibrary(SharedLibraries.nya())

		runScript("integration/Jenkinsfile")

		def deploy  = helper.callStack.findAll { call ->
			call.methodName == 'sh'
		}.collect { call ->
			call.callArgsToString(call)
		}.findAll{ call ->
			call.contains('clean deploy')
		}

		assertThat(deploy.size()).isEqualTo(1)

		//printCallStack()

		assertJobStatusSuccess()
	}
}
