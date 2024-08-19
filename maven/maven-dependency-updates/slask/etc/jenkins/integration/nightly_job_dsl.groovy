import groovy.json.JsonSlurper

URL gerritConfigURL = new URL('http://nya-tools.its.umu.se/jenkins-setup/gerrit.json')

def gerritConfig = new JsonSlurper().parse(gerritConfigURL.newReader())  

folder('slask')

pipelineJob("slask/nightly") {
    description('Runs Integration every night')

    logRotator {
        numToKeep(20)
    }

    parameters {
        stringParam('BRANCH', 'master', 'The branch to build')
    }

    triggers {
        cron('@midnight')
    }

    definition {
        cpsScm {
            scm {
                git {
                    branch('${BRANCH}')
                    remote {
                        credentials(gerritConfig.credentials)
                        url(gerritConfig.url + "/slask")
                    }
                    extensions {
                        cleanAfterCheckout()
                        relativeTargetDirectory("repos/slask")
                        cloneOptions { shallow(false) }
                    }
                }
            }
            scriptPath("repos/slask/etc/jenkins/integration/Jenkinsfile")
        }
    }
}
