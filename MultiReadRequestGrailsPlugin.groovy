import grails.plugin.multiReadRequest.MultiReadServletFilter

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

	def doWithSpring = {
        multiReadServletFilter(MultiReadServletFilter)
    }

	def doWithWebDescriptor = { xml ->
		def contextParam = xml.'context-param'

		contextParam[contextParam.size() - 1] + {
			'filter' {
				'filter-name'('multiReadServletFilter')
				'filter-class'(org.springframework.web.filter.DelegatingFilterProxy.name)
			}
		}

		// and the filter-mapping(s) right after the last filter
		def filter = xml.'filter'

		filter[filter.size() - 1] + {
			'filter-mapping' {
				'filter-name'('multiReadServletFilter')
				'url-pattern'('/api/*')
			}
		}
	}
}
