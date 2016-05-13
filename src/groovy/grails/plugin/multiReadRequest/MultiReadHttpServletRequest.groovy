package grails.plugin.multiReadRequest

import groovy.util.logging.Log4j
import org.apache.commons.io.IOUtils

import javax.servlet.ServletInputStream
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletRequestWrapper

/**
 * Class which is used to wrap a request in order that the wrapped request's input stream can be
 * read once and later be read again in a pseudo fashion by virtue of keeping the original payload
 * as a string which is actually what is returned by subsequent calls to getInputStream().
 */
@Log4j
public class MultiReadHttpServletRequest extends HttpServletRequestWrapper {

    private final byte[] body

    public MultiReadHttpServletRequest(HttpServletRequest httpServletRequest) {
        super(httpServletRequest);
        try {
            InputStream inputStream = httpServletRequest.getInputStream();
            if (inputStream != null) {
                body = IOUtils.toByteArray(inputStream)
            }
        } catch (Exception ex) {
            log.error("Error reading the request body", ex);
        } 
    }

    /**
     * Override of the getReader() method which returns an BufferedReader that sets encoding
     * and reads from the stored body using getInputStream() instead of from the
	 * request's actual InputStream.
     */
	@Override
	public BufferedReader getReader() throws IOException {
		String enc = getCharacterEncoding();
		if(enc == null) enc = "UTF-8";
		return new BufferedReader(new InputStreamReader(getInputStream(), enc));
	}

    /**
     * Override of the getInputStream() method which returns an InputStream that reads from the
     * stored body instead of from the request's actual InputStream.
     */
    @Override
    public ServletInputStream getInputStream()
            throws IOException {

        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(body);
        ServletInputStream inputStream = new ServletInputStream() {
            public int read()
                    throws IOException {
                return byteArrayInputStream.read();
            }
        };
        return inputStream;
    }

}