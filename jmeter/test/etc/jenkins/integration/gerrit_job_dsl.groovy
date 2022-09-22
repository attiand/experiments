import groovy.json.JsonSlurper

URL gerritConfigURL = new URL('http://nya-tools.its.umu.se/jenkins-setup/gerrit.json')

def gerritConfig = new JsonSlurper().parse(gerritConfigURL.newReader())  

folder('test')

pipelineJob("test/integration") {
    description('Integrate Gerrit change to feature branch')

    logRotator {
        numToKeep(20)
    }

    triggers {
        gerrit {
            project("plain:test", ['ant:**'])
            events {
                changeMerged()
            }
            configure { node ->
                node << { serverName(gerritConfig.server) }
                node / silentMode('true')
            }
        }
    }

    definition {
        cpsScm {
            scm {
                git {
                    branch('${GERRIT_BRANCH}')
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
            scriptPath("repos/test/etc/jenkins/integration/Jenkinsfile")
        }
    }
}
