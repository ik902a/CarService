package by.epam.learn.controller.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.learn.controller.command.Command;
import by.epam.learn.controller.command.PagePath;
import by.epam.learn.controller.command.Router;
import by.epam.learn.controller.command.Router.RouteType;

public class LogOutCommand implements Command {
	public static Logger log = LogManager.getLogger();

	@Override
	public Router execute(HttpServletRequest request) {
        log.debug("Logged out");
        Router router = new Router(PagePath.HOME_REDIRECT, RouteType.REDIRECT);//TODO check
    	HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
        return router;
	}
}
