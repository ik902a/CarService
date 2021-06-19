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
import by.epam.learn.entity.User;
import by.epam.learn.exception.ServiceException;
import by.epam.learn.model.service.impl.UserServiceImpl;

/**
 * The {@code ToEditUserCommand} class represents go to edit user page
 * 
 * @author Ihar Klepcha
 */
public class ToEditUserCommand implements Command {
	public static Logger log = LogManager.getLogger();
    private final UserServiceImpl service;

    public ToEditUserCommand(UserServiceImpl service) {
        this.service = service;
    }

	@Override
	public Router execute(HttpServletRequest request) {
		Router router;
		User user;
		String userId = request.getParameter(RequestParameter.ID);
		try {
			user = service.findUserById(userId);
		} catch (ServiceException e) {
			user = new User();
			request.setAttribute(AttributeParameter.EXCEPTION, e);
			log.error("exception while searching user", e);
			router = new Router(PagePath.ERROR, RouteType.FORWARD);
		}
		request.setAttribute(AttributeParameter.USER_DATA, user);// TODO UserInfo
        router = new Router(PagePath.EDIT_USER, RouteType.FORWARD);
		return router;
	}
}
