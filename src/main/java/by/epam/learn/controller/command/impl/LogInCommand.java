package by.epam.learn.controller.command.impl;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.learn.controller.command.Command;
import by.epam.learn.controller.command.MessageKey;
import by.epam.learn.controller.command.PagePath;
import by.epam.learn.controller.command.RequestParameter;
import by.epam.learn.controller.command.Router;
import by.epam.learn.controller.command.AttributeParameter;
import by.epam.learn.controller.command.Router.RouteType;
import by.epam.learn.entity.User;
import by.epam.learn.entity.UserStatus;
import by.epam.learn.exception.ServiceException;
import by.epam.learn.model.service.impl.UserServiceImpl;

import java.util.Optional;

/**
 * The {@code LogInCommand} class represents authentication
 * 
 * @author Ihar Klepcha
 */
public class LogInCommand implements Command {
	public static Logger log = LogManager.getLogger();
    private final UserServiceImpl service;

    public LogInCommand(UserServiceImpl service) {
        this.service = service;
	}

	@Override
	public Router execute(HttpServletRequest request) {
		Router router;
		Optional<User> optionalUser = Optional.empty();
		String login = request.getParameter(RequestParameter.LOGIN);
		String password = request.getParameter(RequestParameter.PASSWORD);
		try {
			optionalUser = service.login(login, password);
		} catch (ServiceException e) {
			request.setAttribute(AttributeParameter.EXCEPTION, e);
			log.error("exception while logging in", e);
			router = new Router(PagePath.ERROR, RouteType.FORWARD);
		}
		if (optionalUser.isPresent()) {
			User user = optionalUser.get();
			String role = user.getRole().toString();
			UserStatus status = user.getStatus();
			if (status == UserStatus.ACTIVE) {
				request.getSession().setAttribute(AttributeParameter.ROLE, role);
				request.getSession().setAttribute(AttributeParameter.USER, user);
				router = new Router(PagePath.MAIN_REDIRECT, RouteType.REDIRECT);
			} else {
				request.setAttribute(AttributeParameter.ERROR_MESSAGE, MessageKey.NOT_ACTIVATED_MESSAGE);
				router = new Router(PagePath.LOGIN, RouteType.FORWARD);
			}
		} else {
			request.setAttribute(AttributeParameter.ERROR_MESSAGE, MessageKey.INCORRECT_LOG_IN_MESSAGE);
			router = new Router(PagePath.LOGIN, RouteType.FORWARD);
		}
		return router;
	}
}
