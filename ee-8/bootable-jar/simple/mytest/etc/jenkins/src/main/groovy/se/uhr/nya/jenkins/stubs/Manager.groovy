package se.uhr.nya.jenkins.stubs

import java.util.regex.Pattern

class Manager {
	Hudson hudson = new Hudson()
	Build build = new Build()

	def getLogMatcher(String regexp) {
		Pattern p = Pattern.compile(regexp)
		p.matcher("");
	}

	def createSummary(String image) {
		new Summary()
	}

	void addShortText(String text) {
	}	
}


class Hudson {
	String rootUrl = 'file://test/'
}

class Build {
	String url = 'url'
}

class Summary {
	def appendText(String html, boolean escape) {
	}
}
