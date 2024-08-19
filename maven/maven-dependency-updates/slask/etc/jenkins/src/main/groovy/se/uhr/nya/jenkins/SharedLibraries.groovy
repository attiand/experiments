package se.uhr.nya.jenkins

import static com.lesfurets.jenkins.unit.global.lib.GitSource.gitSource
import static com.lesfurets.jenkins.unit.global.lib.LibraryConfiguration.library

import com.lesfurets.jenkins.unit.global.lib.LibraryConfiguration


class SharedLibraries {
	static LibraryConfiguration nya() {
		String user = System.getProperty("user.name")
		library()
				.name('nya')
				.retriever(gitSource("ssh://${user}@nya-gerrit.its.umu.se:29418/pipeline-nya"))
				.targetPath(System.getProperty('java.io.tmpdir'))
				.defaultVersion('master')
				.build()
	}
}
