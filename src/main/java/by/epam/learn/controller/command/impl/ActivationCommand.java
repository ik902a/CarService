package by.epam.learn.controller.command.impl;

import jakarta.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.learn.controller.command.Command;
import by.epam.learn.controller.command.MessageKey;
import by.epam.learn.controller.command.PagePath;
import by.epam.learn.controller.command.RequestParameter;
import by.epam.learn.controller.command.Router;
import by.epam.learn.controller.command.Router.RouteType;
import by.epam.learn.controller.command.AttributeParameter;
import by.epam.learn.exception.ServiceException;
import by.epam.learn.model.service.impl.UserServiceImpl;

/**
 * The {@code ActivationCommand} class represents activation of a user
 * 
 * @author Ihar Klepcha
 */
public class ActivationCommand implements Command {
	public static Logger log = LogManager.getLogger();
	private final UserServiceImpl service;

	public ActivationCommand(UserServiceImpl service) {
		this.service = service;
	}

	@Override
	public Router execute(HttpServletRequest request) {
		Router router;
		String login = request.getParameter(RequestParameter.LOGIN);
		try {
			if (service.activate(login)) {
				router = new Router(PagePath.MAIN_REDIRECT, RouteType.FORWARD);
			} else {
				request.setAttribute(AttributeParameter.ERROR_MESSAGE, MessageKey.NOT_ACTIVATED_MESSAGE);
				router = new Router(PagePath.HOME, RouteType.FORWARD);
			}
		} catch (ServiceException e) {
			request.setAttribute(AttributeParameter.EXCEPTION, e);
			log.error("user activation error", e);
			router = new Router(PagePath.ERROR, RouteType.FORWARD);
		}
		return router;
	}
}
