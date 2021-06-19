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

/**
 * The {@code ToEditClientCommand} class represents go to edit client page
 * 
 * @author Ihar Klepcha
 */
public class ToEditClientCommand implements Command {
	public static Logger log = LogManager.getLogger();

	@Override
	public Router execute(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute(RequestParameter.USER);
		String login = user.getLogin();
		String name = user.getName();
		String email = user.getEmail();
		String phone = user.getPhone();
		request.setAttribute(AttributeParameter.LOGIN, login);
		request.setAttribute(AttributeParameter.NAME, name);
		request.setAttribute(AttributeParameter.EMAIL, email);
		if (phone != null) {
			request.setAttribute(AttributeParameter.PHONE, phone);
		}
		Router router = new Router(PagePath.EDIT_CLIENT_PROFILE, RouteType.FORWARD);
		return router;
	}
}
