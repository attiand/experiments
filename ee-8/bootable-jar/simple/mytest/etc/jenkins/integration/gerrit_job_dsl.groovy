import groovy.json.JsonSlurper

URL gerritConfigURL = new URL('http://nya-tools.its.umu.se/jenkins-setup/gerrit.json')

def gerritConfig = new JsonSlurper().parse(gerritConfigURL.newReader())  

folder('slask')

pipelineJob("slask/integration") {
    description('Integrate Gerrit change to feature branch')

    logRotator {
        numToKeep(20)
    }

    triggers {
        gerrit {
            project("plain:slask", ['ant:**'])
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
