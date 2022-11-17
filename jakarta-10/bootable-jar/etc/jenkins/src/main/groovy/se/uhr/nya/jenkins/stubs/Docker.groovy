package se.uhr.nya.jenkins.stubs

class Docker {

	def images = [:]

	def build(def name) {
		return new Image(name: name)	
	}

	def build(def name, def args) {
		return new Image(name: name)
	}
		
	def image(def name) {
		images[name] = new Image(name: name)
		return images[name]
	}

	def withRegistry(url, credentialsId, body) {
		body.call()
	}
}

class Image {

	def name
	def tags = []

	def withRun(def args, def body) {
		withRun(args, '', body)
	}
	
	def withRun(def args, def command, def body) {
		body.call(new Container())
	}
	
	def inside(def body) {
		body.call()
	}

	def inside(def args, def body) {
		body.call()
	}

	def pull() {
	}

	def push(def tag) {
		tags.push(tag)
	}

	def tag(def tag) {
		tags.push(tag)
	}
}

class Container {
	def id = UUID.randomUUID()
	def id() {
		return id.toString()
	}
}
