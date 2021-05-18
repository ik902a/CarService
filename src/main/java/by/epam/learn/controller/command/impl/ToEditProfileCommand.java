package by.epam.learn.controller.command.impl;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.learn.controller.command.Command;
import by.epam.learn.controller.command.PagePath;
import by.epam.learn.controller.command.Router;
import by.epam.learn.controller.command.Router.RouteType;
import by.epam.learn.controller.command.SessionAttribute;
import by.epam.learn.entity.User;

import static by.epam.learn.controller.command.RequestParameter.*;

public class ToEditProfileCommand implements Command {
	public static Logger log = LogManager.getLogger();

	@Override
	public Router execute(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute(SessionAttribute.USER);
		String login = user.getLogin();
		String name = user.getName();
		String email = user.getEmail();
		String phone = user.getPhone();
		
		request.setAttribute(LOGIN, login);
		request.setAttribute(NAME, name);
		request.setAttribute(EMAIL, email);
		request.setAttribute(PHONE, phone);
	
        Router router = new Router(PagePath.EDIT_CLIENT_PROFILE, RouteType.FORWARD);
		return router;
	}

}
