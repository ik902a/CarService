package by.epam.learn.controller.filter;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.learn.controller.command.SessionAttribute;

@WebFilter(dispatcherTypes = { DispatcherType.REQUEST, 
								DispatcherType.FORWARD, 
								DispatcherType.INCLUDE 
								}, urlPatterns = {"/pages/*" })
public class PageFilter implements Filter {
	public static Logger log = LogManager.getLogger();

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		log.debug("PageFilter");
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession();
		String pagePath = httpRequest.getServletPath();
		log.debug("PageFilter page: {}", pagePath);
		session.setAttribute(SessionAttribute.CURRENT_PAGE, pagePath);

		chain.doFilter(request, response);
	}
}
