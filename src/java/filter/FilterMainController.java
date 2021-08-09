package filter;

import dto.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

/**
 *
 * @author thand
 */
@WebFilter(filterName = "FilterMainController", urlPatterns = {"/*"})
public class FilterMainController implements Filter {

    private static final boolean DEBUG = true;
    private FilterConfig filterConfig = null;

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private static final String LOGIN = "login.jsp";
    private static final String ERROR = "error.jsp";
    private static final String WELCOME = "welcome.jsp";

    private static final String LOGIN_CONTROLLER = "LoginController";

    private static final Logger LOGGER = Logger.getLogger(FilterMainController.class);

    public FilterMainController() {
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        String url = ERROR;

        try {
            HttpServletRequest req = (HttpServletRequest) request;
            String uri = req.getRequestURI();

            int lastIndex = uri.lastIndexOf("/");
            String resource = uri.substring(lastIndex + 1);

            HttpSession session = req.getSession();
            User user = (User) session.getAttribute("user");

            if (user != null) {
                if (url.equals("")) {
                    url = WELCOME;
                } else {
                    url = resource;
                }
                if (resource.lastIndexOf(".jsp") > 0 || resource.lastIndexOf(".css") > 0 || resource.lastIndexOf(".js") > 0) {
                    url = resource;
                }
            } else {
                if (resource.equals(LOGIN_CONTROLLER)) {
                    url = LOGIN_CONTROLLER;
                } else {
                    url = LOGIN;
                }
            }

            if (url != null) {
                request.getRequestDispatcher(url).forward(request, response);
            } else {
                chain.doFilter(request, response);
            }
        } catch (Exception e) {
            LOGGER.error(e);
            request.setAttribute("errorMess", e.toString());
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (DEBUG) {
                log("FilterMainController:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("FilterMainController()");
        }
        StringBuffer sb = new StringBuffer("FilterMainController(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }

    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return stackTrace;
    }

    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }
}
