package by.epam.learn.controller.command.impl;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.learn.controller.command.Command;
import by.epam.learn.controller.command.PagePath;
import by.epam.learn.controller.command.RequestParameter;
import by.epam.learn.controller.command.Router;
import by.epam.learn.controller.command.Router.RouteType;
import by.epam.learn.controller.command.AttributeParameter;

/**
 * The {@code ChangeLocaleCommand} class represents switch language and local
 * 
 * @author Ihar Klepcha
 */
public class ChangeLocaleCommand implements Command {
	public static Logger log = LogManager.getLogger();

	@Override
	public Router execute(HttpServletRequest request) {
		Router router;
		String newLocale = request.getParameter(RequestParameter.LOCALE);
		log.debug("Locale changed to {}", newLocale);
		request.getSession().setAttribute(AttributeParameter.LOCALE, newLocale);
		String language = newLocale.substring(0, 2);
		request.getSession().setAttribute(AttributeParameter.LANGUAGE, language);
		log.debug("Set language = {}", language);
		String page = (String) request.getSession().getAttribute(RequestParameter.CURRENT_PAGE);
		log.debug("Current page = {}", page);	
		if (page != null) {
			page = page.replaceFirst("/DemoCarService/", "");
			log.debug("Current page = {}", page);
			router = new Router(PagePath.MAIN_REDIRECT, RouteType.FORWARD);
		} else {
			router = new Router(PagePath.HOME, RouteType.REDIRECT);
		}
		return router;
	}
}
