package ro.ubb.istudent.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CorsFilter implements Filter {

    private final SecurityTokenProperties tokenProperties;

    @Autowired
    public CorsFilter(SecurityTokenProperties tokenProperties) {
        this.tokenProperties = tokenProperties;
    }

    public void init(FilterConfig filterConfig) {
    }

    /**
     * The <code>doFilter</code> method of the Filter is called by the container
     * each time a request/response pair is passed through the chain due to a
     * client request for a resource at the end of the chain. The FilterChain
     * passed in to this method allows the Filter to pass on the request and
     * response to the next entity in the chain.
     * <p>
     * Directly set headers on the response after invocation of the next
     * entity in the filter chain.
     *
     * @param req   The request to process
     * @param res   The response associated with the request
     * @param chain Provides access to the next filter in the chain for this
     *              filter to pass the request and response to for further
     *              processing
     * @throws IOException      if an I/O error occurs during this filter's
     *                          processing of the request
     * @throws ServletException if the processing fails for any other reason
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept , Cache-Control , " +
                tokenProperties.getHeader());
        chain.doFilter(req, res);
    }

    public void destroy() {
    }

}
