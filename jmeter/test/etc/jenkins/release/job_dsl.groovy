import groovy.json.JsonSlurper

URL gerritConfigURL = new URL('http://nya-tools.its.umu.se/jenkins-setup/gerrit.json')

def gerritConfig = new JsonSlurper().parse(gerritConfigURL.newReader())  

folder('test')

pipelineJob("test/release") {
    description("Release master branch of test. Make sure POM version is updated. <b>Note</b> that this job is interactive")

    logRotator {
        numToKeep(20)
    }

    parameters {
        stringParam('BRANCH', 'master', 'The branch to release, should be master unless releasing a emergency patch')
    }

    definition {
        cpsScm {
            scm {
                git {
                    branch('${BRANCH}')
                    remote {
                        credentials(gerritConfig.credentials)
                        url(gerritConfig.url + "/test")
                    }
                    extensions {
                        cleanAfterCheckout()
                        relativeTargetDirectory("repos/test")
                        cloneOptions { shallow(false) }
                    }
                }
            }
            scriptPath("repos/test/etc/jenkins/release/Jenkinsfile")
        }
    }
}
