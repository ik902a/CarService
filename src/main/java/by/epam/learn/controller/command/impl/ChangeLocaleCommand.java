package by.epam.learn.controller.command.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.learn.controller.command.Command;
import by.epam.learn.controller.command.RequestParameter;
import by.epam.learn.controller.command.SessionAttribute;

public class ChangeLocaleCommand implements Command {
	public static Logger log = LogManager.getLogger();
	
	private static final Pattern JSP_PATTERN = Pattern.compile("/pages/.*\\.jsp");

	@Override
	public String execute(HttpServletRequest request) {
		String newLocale = request.getParameter(RequestParameter.LOCALE);
		log.debug("Locale changed to {}", newLocale);
		request.getSession().setAttribute(SessionAttribute.LOCALE, newLocale);
		String language = newLocale.substring(0, 2);
		request.getSession().setAttribute(SessionAttribute.LANGUAGE, language);
		log.debug("Set language = {}", language);
		String fullPagePath = (String) request.getSession().getAttribute(SessionAttribute.CURRENT_PAGE);
		log.debug("Current page = {}", fullPagePath);
		Matcher matcher = JSP_PATTERN.matcher(fullPagePath);
		String page = null;
		if (matcher.find()) {
			page = matcher.group();
		}
		return page;
	}
}
