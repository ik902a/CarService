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
import by.epam.learn.controller.command.Router.RouteType;
import by.epam.learn.controller.command.AttributeParameter;
import by.epam.learn.exception.ServiceException;
import by.epam.learn.model.service.impl.UserServiceImpl;

import static by.epam.learn.controller.command.RequestParameter.*;
import static by.epam.learn.controller.command.DataKeyword.*;

/**
 * The {@code SignUpCommand} class represents sign up user
 * 
 * @author Ihar Klepcha
 */
public class SignUpCommand implements Command {
	public static Logger log = LogManager.getLogger();
	private final UserServiceImpl service;

	public SignUpCommand(UserServiceImpl service) {
		this.service = service;
	}

	@Override
	public Router execute(HttpServletRequest request) {
		List<String> errorMessageList = new ArrayList<>();
		Router router;
		String loginValue = request.getParameter(LOGIN);
		String nameValue = request.getParameter(NAME);
		String emailValue = request.getParameter(EMAIL);
		String passValue = request.getParameter(PASSWORD);
		String passConfirmValue = request.getParameter(PASSWORD_CONFIRMING);
		Map<String, String> userData = new HashMap<>();
		userData.put(LOGIN_KEY, loginValue);
		userData.put(NAME_KEY, nameValue);
		userData.put(EMAIL_KEY, emailValue);
		userData.put(PASSWORD_KEY, passValue);
		userData.put(CONFIRMING_PASSWORD_KEY, passConfirmValue);
		try {
			boolean isSignUp = service.signUp(userData);
			if (isSignUp) {
				router = new Router(PagePath.HOME, RouteType.REDIRECT);
			} else {
				loginValue = userData.get(LOGIN_KEY);
				nameValue = userData.get(NAME_KEY);
				emailValue = userData.get(EMAIL_KEY);
				passValue = userData.get(PASSWORD_KEY);
				passConfirmValue = userData.get(CONFIRMING_PASSWORD_KEY);
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
				if (passValue.contains(INCORRECT_VALUE)) {
					errorMessageList.add(MessageKey.INCORRECT_PASSWORD_MESSAGE);
				}
				if (passConfirmValue.contains(DOESNT_MATCH)) {
					errorMessageList.add(MessageKey.PASSWORD_DOESNT_MATH_MESSAGE);
				}
				request.setAttribute(AttributeParameter.ERROR_MESSAGE_LIST, errorMessageList);
				router = new Router(PagePath.SIGNUP, RouteType.FORWARD);
			}
		} catch (ServiceException e) {
			request.setAttribute(AttributeParameter.EXCEPTION, e);
			log.error("exception while signing up", e);
			router = new Router(PagePath.ERROR, RouteType.FORWARD);
		}
		return router;
	}
}
