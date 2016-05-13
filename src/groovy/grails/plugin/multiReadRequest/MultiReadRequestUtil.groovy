package grails.plugin.multiReadRequest

import org.codehaus.groovy.grails.web.json.JSONObject
import java.util.concurrent.ConcurrentHashMap

class MultiReadRequestUtil {
	
	static void processNestedKeys(Map requestMap, String key) {
		if (getParameterValue(requestMap, key) instanceof JSONObject) {
			String nestedPrefix = key + ".";
			Map nestedMap = getParameterValue(requestMap, key)
			for (Map.Entry<String, Object> entry : nestedMap.entrySet()) {
				String newKey = nestedPrefix + entry.key;
				String value = getParameterValue(nestedMap, entry.key)
				if(value) {
					requestMap.put(newKey, value)
				}
				processNestedKeys(requestMap, "${nestedPrefix + entry.key}");
			}
		}
	}

	static Map populateParamsFromRequestJSON(def json) {
		Map requestParameters = json as ConcurrentHashMap
		for (Map.Entry<String, Object> entry : requestParameters.entrySet()) {
			processNestedKeys(requestParameters, entry.key)
		}
		requestParameters
	}
	
	static Object getParameterValue(Map requestMap, String key) {
		Object paramValue = requestMap.get(key);
		if (paramValue && paramValue instanceof String[]) {
			if (((String[]) paramValue).length == 1) {
				paramValue = ((String[]) paramValue)[0];
			}
		}
		return paramValue;
	}
}