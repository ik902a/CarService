package by.epam.learn.controller.command.impl;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.learn.controller.command.Command;
import by.epam.learn.controller.command.PagePath;
import by.epam.learn.controller.command.SessionAttribute;

import by.epam.learn.entity.UserRole;

public class ToProfileCommand implements Command {
	public static Logger log = LogManager.getLogger();

	@Override
	public String execute(HttpServletRequest request) {
		String page;
		String currentRole = (String) request.getSession().getAttribute(SessionAttribute.ROLE);
		UserRole typeRole = UserRole.valueOf(currentRole.toUpperCase());
		switch (typeRole) {
		case ADMIN:
			page = PagePath.ADMIN_PROFILE;
			break;
		case MANAGER:
			page = PagePath.MANAGER_PROFILE;
			break;
		case MECHANIC:
			page = PagePath.MECHANIC_PROFILE;
			break;
		case CLIENT:
			page = PagePath.CLIENT_PROFILE;
			break;
		default:
			log.error("incorrect role type");
			page = PagePath.HOME;
			break;
		}
		return page;
	}
}
