package se.uhr.nya.jenkins

import static com.lesfurets.jenkins.unit.MethodSignature.method
import static org.assertj.core.api.Assertions.*

import org.junit.Before
import org.junit.Test

import se.uhr.nya.jenkins.stubs.Manager
import se.uhr.nya.jenkins.stubs.Docker
import se.uhr.nya.jenkins.stubs.TestResult

import com.lesfurets.jenkins.unit.BasePipelineTest


public class GerritPipelineTest extends BasePipelineTest {

	def changeId = 'I139690047f003e6629fcb3d206495dc39a805775'
	def commitId = '1768ddcc494fa97182b3c8fbc98bd34369d80cdb'

	@Override
	@Before
	void setUp() throws Exception {
		super.setUp()
		helper.registerAllowedMethod("timestamps", [Closure.class], null)
		helper.registerAllowedMethod("withSonarQubeEnv", [String.class, Closure.class], null)
		helper.registerAllowedMethod("ansiColor", [String.class, Closure.class], null)
		helper.registerAllowedMethod('junit', [String.class], { path -> new TestResult()})
		
		getBinding().setVariable('scm', [:])
		getBinding().setVariable('docker', new Docker())
		getBinding().setVariable('manager', new Manager())

		helper.registerAllowedMethod(method("load", String.class), { file ->
			return helper.runScript(new File(file).name, binding)
		})

		getBinding().setVariable('params', [GERRIT_BRANCH: 'master', GERRIT_PROJECT: 'slask', GERRIT_PATCHSET_REVISION: commitId, GERRIT_CHANGE_ID: changeId, GERRIT_HOST: 'nya-gerrit.its.umu.se'])
	}

	@Test
	void shouldRunSuccessScenario() throws Exception {
		helper.registerSharedLibrary(SharedLibraries.nya())

		runScript("gerrit/Jenkinsfile")

		def mvnDeploy = helper.callStack.findAll { call ->
			call.methodName == 'sh'
		}.collect { call ->
			call.callArgsToString(call)
		}.findAll{ call ->
			call.contains('mvn')
		}.findAll{ call ->
			call.endsWith('compile')
		}

		assertThat(mvnDeploy.size()).isEqualTo(1)

		def gerritVotes  = helper.callStack.findAll { call ->
			call.methodName == 'sh'
		}.collect { call ->
			call.callArgsToString(call)
		}.findAll{ call ->
			call.contains('gerrit review')
		}

		assertThat(gerritVotes.size()).isEqualTo(2)
		assertThat(gerritVotes[0]).contains('Compile-Verified')
		assertThat(gerritVotes[1]).contains('Unittest-Verified')

		//printCallStack()

		assertJobStatusSuccess()
	}
}
