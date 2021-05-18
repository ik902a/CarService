package by.epam.learn.controller.command.impl;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.learn.controller.command.Command;
import by.epam.learn.controller.command.MessageKey;
import by.epam.learn.controller.command.PagePath;
import by.epam.learn.controller.command.RequestParameter;
import by.epam.learn.controller.command.Router;
import by.epam.learn.controller.command.SessionAttribute;
import by.epam.learn.controller.command.Router.RouteType;
import by.epam.learn.entity.User;
import by.epam.learn.entity.UserRole;
import by.epam.learn.entity.UserStatus;
import by.epam.learn.exception.ServiceException;
import by.epam.learn.model.service.impl.UserServiceImpl;

import static by.epam.learn.controller.command.RequestParameter.*;

import java.util.Optional;

public class LogInCommand implements Command {
	public static Logger log = LogManager.getLogger();
    private final UserServiceImpl service;

    public LogInCommand(UserServiceImpl service) {
        this.service = service;
	}

	@Override
	public Router execute(HttpServletRequest request) {
		Router router;
		String login = request.getParameter(LOGIN);
		String password = request.getParameter(PASSWORD);
		Optional<User> optionalUser = Optional.empty();
		try {
			optionalUser = service.login(login, password);
		} catch (ServiceException e) {
			log.error("Exception while logging in", e);
			request.setAttribute("error_message", "Exception while logging in: " + e.getMessage());
		}
		User user;
		if (optionalUser.isPresent()) {
			user = optionalUser.get();
			UserRole role = user.getRole();
			UserStatus status = user.getStatus();

			if (status.equals(UserStatus.ACTIVE)) {
				request.getSession().setAttribute(SessionAttribute.ROLE, role);
				request.getSession().setAttribute(SessionAttribute.USER, user);
				router = new Router(PagePath.MAIN_REDIRECT, RouteType.REDIRECT);// TODO check
			} else {
				request.setAttribute(RequestParameter.ERROR_MESSAGE, MessageKey.NOT_ACTIVATED_MESSAGE);
				router = new Router(PagePath.LOGIN, RouteType.FORWARD);// TODO пойдет форвард?
			}
		} else {
			request.setAttribute(RequestParameter.ERROR_MESSAGE, MessageKey.INCORRECT_LOG_IN_MESSAGE);// если редирект, то в сессию?
			router = new Router(PagePath.LOGIN, RouteType.FORWARD);// TODO пойдет форвард?
		}
		return router;
	}
}
