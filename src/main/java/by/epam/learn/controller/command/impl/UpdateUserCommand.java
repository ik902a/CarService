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
import by.epam.learn.controller.command.RequestParameter;
import by.epam.learn.controller.command.Router;
import by.epam.learn.controller.command.Router.RouteType;
import by.epam.learn.controller.command.AttributeParameter;
import by.epam.learn.entity.User;
import by.epam.learn.entity.UserRole;
import by.epam.learn.entity.UserStatus;
import by.epam.learn.exception.ServiceException;
import by.epam.learn.model.service.impl.UserServiceImpl;

import static by.epam.learn.controller.command.DataKeyword.*;

/**
 * The {@code UpdateUserCommand} class updates user
 * 
 * @author Ihar Klepcha
 */
public class UpdateUserCommand implements Command {
	public static Logger log = LogManager.getLogger();
	private final UserServiceImpl service;

	public UpdateUserCommand(UserServiceImpl service) {
		this.service = service;
	}

	@Override
	public Router execute(HttpServletRequest request) {
		List<String> errorMessageList = new ArrayList<>();
		Router router;
		String oldLogin = request.getParameter(RequestParameter.OLD_LOGIN);
		String userIdValue = request.getParameter(RequestParameter.ID);
		String loginValue = request.getParameter(RequestParameter.LOGIN);
		String nameValue = request.getParameter(RequestParameter.NAME);
		String emailValue = request.getParameter(RequestParameter.EMAIL);
		String phoneValue = request.getParameter(RequestParameter.PHONE);
		String roleValue = request.getParameter(RequestParameter.ROLE);
		String statusValue = request.getParameter(RequestParameter.STATUS);
		Map<String, String> userData = new HashMap<>();
		userData.put(ID_KEY, userIdValue);
		userData.put(LOGIN_KEY, loginValue);
		userData.put(NAME_KEY, nameValue);
		userData.put(EMAIL_KEY, emailValue);
		userData.put(PHONE_KEY, phoneValue);
		userData.put(ROLE_KEY, roleValue);
		userData.put(STATUS_KEY, statusValue);
		try {
			boolean isUpdate = service.updateUser(userData, oldLogin);
			if (isUpdate) {
				router = new Router(PagePath.PROFILE_REDIRECT, RouteType.REDIRECT);
			} else {	
				String loginCheck = userData.get(LOGIN_KEY);
				String nameCheck = userData.get(NAME_KEY);
				String emailCheck = userData.get(EMAIL_KEY);
				String phoneCheck = userData.get(PHONE_KEY);
				if (loginCheck.contains(INCORRECT_VALUE)) {
					errorMessageList.add(MessageKey.INCORRECT_LOGIN_MESSAGE);
				}
				if (loginCheck.contains(ALREADY_EXISTS)) {
					errorMessageList.add(MessageKey.EXISTS_LOGIN_MESSAGE);
				}
				if (nameCheck.contains(INCORRECT_VALUE)) {
					errorMessageList.add(MessageKey.INCORRECT_NAME_MESSAGE);
				}
				if (emailCheck.contains(INCORRECT_VALUE)) {
					errorMessageList.add(MessageKey.INCORRECT_EMAIL_MESSAGE);
				}
				if (phoneCheck.contains(INCORRECT_VALUE)) {
					errorMessageList.add(MessageKey.INCORRECT_PHONE_MESSAGE);
				}
				User user = new User (Long.parseLong(userIdValue), loginValue, nameValue, emailValue, 
						phoneValue, UserRole.valueOf(roleValue), UserStatus.valueOf(statusValue));
				request.setAttribute(AttributeParameter.USER_DATA, user);
				request.setAttribute(AttributeParameter.ERROR_MESSAGE_LIST, errorMessageList);
				router = new Router(PagePath.EDIT_USER, RouteType.FORWARD);
			}
		} catch (ServiceException e) {
			request.setAttribute(AttributeParameter.EXCEPTION, e);
			log.error("exception while update user", e);
			router = new Router(PagePath.ERROR, RouteType.FORWARD);
		}
		return router;
	}
}
