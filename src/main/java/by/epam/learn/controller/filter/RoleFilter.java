package by.epam.learn.controller.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.learn.controller.command.RequestParameter;

/**
 * The {@code RoleFilter} class for control roles
 * 
 * @author Ihar Klepcha
 * @see Filter
 */
@WebFilter(filterName = "RoleFilter", urlPatterns = { "/controller" }, servletNames = { "controller" }, 
initParams = {@WebInitParam(name = "INDEX_PATH", value = "/index.jsp") })
public class RoleFilter implements Filter {
	public static Logger log = LogManager.getLogger();
	private String indexPath;

	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		indexPath = fConfig.getInitParameter("INDEX_PATH");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		log.info("ROLE filter");
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession(true);
		if (session.getAttribute(RequestParameter.ROLE) == null) {
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(indexPath);
			dispatcher.forward(request, response);
			return;
		}
		chain.doFilter(request, response);
	}
}
