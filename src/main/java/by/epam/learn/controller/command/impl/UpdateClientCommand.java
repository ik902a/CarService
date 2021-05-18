package by.epam.learn.controller.command.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.learn.controller.command.Command;
import by.epam.learn.controller.command.MessageKey;
import by.epam.learn.controller.command.PagePath;
import by.epam.learn.controller.command.Router;
import by.epam.learn.controller.command.SessionAttribute;
import by.epam.learn.controller.command.Router.RouteType;
import by.epam.learn.entity.User;
import by.epam.learn.exception.ServiceException;
import by.epam.learn.model.service.impl.UserServiceImpl;

import static by.epam.learn.controller.command.RequestParameter.*;
import static by.epam.learn.controller.command.DataKeyword.*;

public class UpdateClientCommand implements Command {
	public static Logger log = LogManager.getLogger();
	private final UserServiceImpl service;

	public UpdateClientCommand(UserServiceImpl service) {
		this.service = service;
	}

	@Override
	public Router execute(HttpServletRequest request) {
		List<String> errorMessageList = new ArrayList<>();
		Router router;
		User user = (User) request.getSession().getAttribute(SessionAttribute.USER);
		String loginValue = request.getParameter(LOGIN);
		String nameValue = request.getParameter(NAME);
		String emailValue = request.getParameter(EMAIL);
		String phoneValue = request.getParameter(PHONE);

		Map<String, String> userData = new HashMap<>();
		userData.put(LOGIN_KEY, loginValue);
		userData.put(NAME_KEY, nameValue);
		userData.put(EMAIL_KEY, emailValue);
		userData.put(PHONE_KEY, phoneValue);
		boolean isUpdate;
		try {
			isUpdate = service.updateClient(userData, user);

			if (isUpdate) {
				user.setLogin(loginValue);
				user.setName(nameValue);
				user.setEmail(emailValue);
				user.setPhone(phoneValue);
				request.getSession().setAttribute(SessionAttribute.USER, user);
				router = new Router(PagePath.PROFILE_REDIRECT, RouteType.REDIRECT);//TODO
			} else {				
				loginValue = userData.get(LOGIN_KEY);
				nameValue = userData.get(NAME_KEY);
				emailValue = userData.get(EMAIL_KEY);
				phoneValue = userData.get(PHONE_KEY);
				if (loginValue.contains(INCORRECT_VALUE)) {
					errorMessageList.add(MessageKey.INCORRECT_LOGIN_MESSAGE);
				}
				if (loginValue.contains(ALREADY_EXISTS)) {
					errorMessageList.add(MessageKey.EXISTS_LOGIN_MESSAGE);
				}
				if (nameValue.contains(INCORRECT_VALUE)) {
					errorMessageList.add(MessageKey.INCORRECT_NAME_MESSAGE);
				}
				if (emailValue.contains(INCORRECT_VALUE)) {
					errorMessageList.add(MessageKey.INCORRECT_EMAIL_MESSAGE);
				}
				if (phoneValue.contains(INCORRECT_VALUE)) {
					errorMessageList.add(MessageKey.INCORRECT_PHONE_MESSAGE);
				}
				request.setAttribute(ERROR_MESSAGE_LIST, errorMessageList);
				router = new Router(PagePath.EDIT_CLIENT_PROFILE, RouteType.FORWARD);//TODO Пойдет форвард?
			}
		} catch (ServiceException e) {
			log.error("Exception while signing up", e);
			router = new Router(PagePath.ERROR, RouteType.REDIRECT);
		}
		return router;
	}
}
