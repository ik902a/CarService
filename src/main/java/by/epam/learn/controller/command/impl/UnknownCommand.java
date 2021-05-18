package by.epam.learn.controller.command.impl;

import javax.servlet.http.HttpServletRequest;

import by.epam.learn.controller.command.Command;
import by.epam.learn.controller.command.PagePath;
import by.epam.learn.controller.command.Router;
import by.epam.learn.controller.command.Router.RouteType;

public class UnknownCommand implements Command {

	@Override
	public Router execute(HttpServletRequest request) {
		Router router = new Router(PagePath.HOME, RouteType.REDIRECT);
		return router;
	}
}
