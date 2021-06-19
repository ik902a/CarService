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
import by.epam.learn.controller.command.AttributeParameter;
import by.epam.learn.controller.command.Router.RouteType;
import by.epam.learn.entity.User;
import by.epam.learn.exception.ServiceException;
import by.epam.learn.model.service.impl.UserServiceImpl;

import static by.epam.learn.controller.command.RequestParameter.*;
import static by.epam.learn.controller.command.DataKeyword.*;

/**
 * The {@code UpdateClientCommand} class updates client
 * 
 * @author Ihar Klepcha
 */
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
		User client = (User) request.getSession().getAttribute(USER);
		String loginValue = request.getParameter(LOGIN);
		String nameValue = request.getParameter(NAME);
		String emailValue = request.getParameter(EMAIL);
		String phoneValue = request.getParameter(PHONE);
		Map<String, String> userData = new HashMap<>();
		userData.put(LOGIN_KEY, loginValue);
		userData.put(NAME_KEY, nameValue);
		userData.put(EMAIL_KEY, emailValue);
		userData.put(PHONE_KEY, phoneValue);
		try {
			boolean isUpdate = service.updateClient(userData, client);
			if (isUpdate) {
				client.setLogin(loginValue);
				client.setName(nameValue);
				client.setEmail(emailValue);
				client.setPhone(phoneValue);
				request.getSession().setAttribute(AttributeParameter.USER, client);
				router = new Router(PagePath.PROFILE_REDIRECT, RouteType.REDIRECT);
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
				request.setAttribute(AttributeParameter.ERROR_MESSAGE_LIST, errorMessageList);
				router = new Router(PagePath.TO_EDIT_CLIENT_REDIRECT, RouteType.FORWARD);
			}
		} catch (ServiceException e) {
			request.setAttribute(AttributeParameter.EXCEPTION, e);
			log.error("exception while updating client", e);
			router = new Router(PagePath.ERROR, RouteType.FORWARD);
		}
		return router;
	}
}
