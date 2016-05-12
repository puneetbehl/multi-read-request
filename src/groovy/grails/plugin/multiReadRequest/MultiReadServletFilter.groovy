package grails.plugin.multiReadRequest

import org.springframework.web.filter.GenericFilterBean

import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

public class MultiReadServletFilter implements GenericFilterBean {

    private static final Set<String> MULTI_READ_HTTP_METHODS = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER) {{
        // Enable Multi-Read for PUT and POST requests
            add("PUT");
            add("POST");
    }};

	@Override
    void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if(servletRequest instanceof HttpServletRequest) {
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            // Check wether the current request needs to be able to support the body to be read multiple times
            if(MULTI_READ_HTTP_METHODS.contains(request.getMethod())) {
                // Override current HttpServletRequest with custom implementation
                filterChain.doFilter(new MultiReadHttpServletRequest(request), servletResponse);
                return;
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

}