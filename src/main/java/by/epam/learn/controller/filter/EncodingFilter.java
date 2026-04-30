package by.epam.learn.controller.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The {@code EncodingFilter} class for setting the encoding
 * 
 * @author Ihar Klepcha
 * @see Filter
 */
@WebFilter(filterName = "EncodingFilter", urlPatterns = { "/*" }, initParams = {
		@WebInitParam(name = "encoding", value = "UTF-8", description = "Encoding Param") })
public class EncodingFilter implements Filter {
	public static Logger log = LogManager.getLogger();
	private static final String INIT_PARAMETER = "encoding";
	private String code;

	@Override
	public void init(FilterConfig fConfig) {
		code = fConfig.getInitParameter(INIT_PARAMETER);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		log.debug("EncodingFilter");
		String codeRequest = request.getCharacterEncoding();
		if (codeRequest == null || !codeRequest.equalsIgnoreCase(code)) {
			request.setCharacterEncoding(code);
			response.setCharacterEncoding(code);
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		code = null;
	}
}
