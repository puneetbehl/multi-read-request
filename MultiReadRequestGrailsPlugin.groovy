class MultiReadRequestGrailsPlugin {
    def version = "0.1"
    def grailsVersion = "2.5 > *"
    def pluginExcludes = [
        "grails-app/views/error.gsp"
    ]

    def title = "Multi Read Request Plugin" // Headline display name of the plugin
    def author = "Puneet Behl"
    def authorEmail = "puneet.behl007@gmail.com"
    def description = '''\
Brief summary/description of the plugin.
'''
    def documentation = "http://puneetbehl.github.io/multi-read-request"
    def license = "APACHE"
    def organization = [ name: "TO THE NEW Digital", url: "http://www.tothenew.com/" ]

    // Any additional developers beyond the author specified above.
	def developers = [ 
		[ name: "Puneet Behl", email: "puneet.behl007@gmail.com" ],
		[ name: "Roni C Thomas", email: "roni.thomas@tothenew.com" ]
	]
    def issueManagement = [ system: "GitHub", url: "https://github.com/puneetbehl/multi-read-request/issues" ]
    def scm = [ url: "https://github.com/puneetbehl/multi-read-request" ]

	def doWithWebDescriptor = { xml ->
		
	}

    def doWithSpring = {
        // TODO Implement runtime spring config (optional)
    }

    def doWithDynamicMethods = { ctx ->
        // TODO Implement registering dynamic methods to classes (optional)
    }

    def doWithApplicationContext = { ctx ->
        // TODO Implement post initialization spring config (optional)
    }

    def onChange = { event ->
        // TODO Implement code that is executed when any artefact that this plugin is
        // watching is modified and reloaded. The event contains: event.source,
        // event.application, event.manager, event.ctx, and event.plugin.
    }

    def onConfigChange = { event ->
        // TODO Implement code that is executed when the project configuration changes.
        // The event is the same as for 'onChange'.
    }

    def onShutdown = { event ->
        // TODO Implement code that is executed when the application shuts down (optional)
    }
}
