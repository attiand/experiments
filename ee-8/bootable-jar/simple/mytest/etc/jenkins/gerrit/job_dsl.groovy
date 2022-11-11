import groovy.json.JsonSlurper

URL gerritConfigURL = new URL('http://nya-tools.its.umu.se/jenkins-setup/gerrit.json')

def gerritConfig = new JsonSlurper().parse(gerritConfigURL.newReader())  

folder('slask')

pipelineJob("slask/gerrit") {
    description('Builds and verifies every Gerrit patch set')

    logRotator {
        numToKeep(20)
    }

    triggers {
        gerrit {
            project("plain:slask", ['ant:**'])
            events {
                draftPublished()
                patchsetCreated()
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
                    branch('${GERRIT_PATCHSET_REVISION}')
                    remote {
                        credentials(gerritConfig.credentials)
                        url(gerritConfig.url + "/slask")
                        refspec('+refs/heads/*:refs/remotes/origin/* +refs/changes/*:refs/remotes/origin/changes/*')

                    }
                    extensions {
                        cleanAfterCheckout()
                        relativeTargetDirectory("repos/slask")
                        cloneOptions { shallow(true) }
                    }
                }
            }
            scriptPath("repos/slask/etc/jenkins/gerrit/Jenkinsfile")
        }
    }
}
