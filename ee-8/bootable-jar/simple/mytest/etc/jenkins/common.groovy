import se.uhr.nya.build.Message
import static se.uhr.nya.build.Message.Recipient.CM
import static se.uhr.nya.build.Message.Recipient.DEVELOPERS


BUILD_IMAGE = 'nya-artefact-docker.its.umu.se/build-maven-centjdk11:3.6.3-1'

void postBuildFailedNotification() {
	def subject = "${env.JOB_NAME} failed";
	def body = "${env.JOB_NAME} build #${env.BUILD_NUMBER} failed!\n\nCheck console output at ${env.BUILD_URL}"
	def message = new Message(this)

	if (currentBuild.changeSets.isEmpty()) {
		message.send(recipients: [CM], message: subject, details: body)
	} else {
		message.send(recipients: [DEVELOPERS], message: subject, details: body)
	}
}

this
