package by.epam.learn.controller.command.impl;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.learn.controller.command.Command;
import by.epam.learn.controller.command.PagePath;
import by.epam.learn.controller.command.RequestParameter;
import by.epam.learn.controller.command.Router;
import by.epam.learn.controller.command.Router.RouteType;
import by.epam.learn.controller.command.SessionAttribute;

public class ChangeLocaleCommand implements Command {
	public static Logger log = LogManager.getLogger();

	@Override
	public Router execute(HttpServletRequest request) {
		Router router;
		String newLocale = request.getParameter(RequestParameter.LOCALE);
		log.debug("Locale changed to {}", newLocale);
		request.getSession().setAttribute(SessionAttribute.LOCALE, newLocale);
		String language = newLocale.substring(0, 2);
		request.getSession().setAttribute(SessionAttribute.LANGUAGE, language);
		log.debug("Set language = {}", language);
		String page = (String) request.getSession().getAttribute(SessionAttribute.CURRENT_PAGE);
		log.debug("Current page = {}", page);
		
		if (page != null) {
			router = new Router(page, RouteType.FORWARD);
		} else {
			router = new Router(PagePath.HOME, RouteType.REDIRECT);
		}
		return router;
	}
}
