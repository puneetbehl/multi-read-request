package grails.plugin.multiReadRequest

class ApplicationFilters {

    def filters = {
        apiCalls(uri: '/api/**') {
            before = {
				try {
					if (request.JSON) {
						Map m = request.JSON
						params.putAll(m)
					}
					} catch (Exception e) {
						log.error e.message
						e.printStackTrace(System.out)
					}
            }
        }
    }
}
