package by.epam.learn.controller.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.learn.controller.command.Command;
import by.epam.learn.controller.command.PagePath;
import by.epam.learn.controller.command.Router;
import by.epam.learn.controller.command.Router.RouteType;

/**
 * The {@code LogOutCommand} class represents log out
 * 
 * @author Ihar Klepcha
 */
public class LogOutCommand implements Command {
	public static Logger log = LogManager.getLogger();

	@Override
	public Router execute(HttpServletRequest request) {
        log.info("Logged out");
    	HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
        Router router = new Router(PagePath.HOME_REDIRECT, RouteType.REDIRECT);
        return router;
	}
}
