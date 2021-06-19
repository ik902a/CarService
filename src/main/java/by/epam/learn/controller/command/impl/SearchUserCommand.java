package by.epam.learn.controller.command.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.learn.controller.command.Command;
import by.epam.learn.controller.command.PagePath;
import by.epam.learn.controller.command.RequestParameter;
import by.epam.learn.controller.command.Router;
import by.epam.learn.controller.command.Router.RouteType;
import by.epam.learn.controller.command.AttributeParameter;
import by.epam.learn.entity.User;
import by.epam.learn.exception.ServiceException;
import by.epam.learn.model.service.impl.UserServiceImpl;

/**
 * The {@code SearchUserCommand} class represents search user
 * 
 * @author Ihar Klepcha
 */
public class SearchUserCommand implements Command {
	public static Logger log = LogManager.getLogger();
	private final UserServiceImpl service;

	public SearchUserCommand(UserServiceImpl service) {
		this.service = service;
	}

	@Override
	public Router execute(HttpServletRequest request) {
		Router router;
		String loginValue = request.getParameter(RequestParameter.LOGIN);
		try {
			List<User> users = service.findUserByLogin(loginValue);
			if (!users.isEmpty()) {
				request.setAttribute(AttributeParameter.USER_LIST, users);
			}
			router = new Router(PagePath.ADMIN_PROFILE, RouteType.FORWARD);
		} catch (ServiceException e) {
			request.setAttribute(AttributeParameter.EXCEPTION, e);
			log.error("exception while finding user", e);
			router = new Router(PagePath.ERROR, RouteType.FORWARD);
		}
		return router;
	}
}
