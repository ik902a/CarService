package by.epam.learn.controller.command.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.learn.controller.command.Command;
import by.epam.learn.controller.command.PagePath;
import by.epam.learn.controller.command.SessionAttribute;
import by.epam.learn.entity.UserRole;

public class LogOutCommand implements Command {
	public static Logger log = LogManager.getLogger();
	
	private static final Pattern JSP_PATTERN = Pattern.compile("/pages/.*\\.jsp");

	@Override
	public String execute(HttpServletRequest request) {//TODO
        log.debug("Logged out");
        
        String fullPagePath = (String) request.getSession().getAttribute(SessionAttribute.CURRENT_PAGE);
        
        Matcher matcher = JSP_PATTERN.matcher(fullPagePath);
        String page = null;
        if (matcher.find()) {
            page = PagePath.HOME;
        }
        request.getSession().invalidate();
        request.getSession().setAttribute(SessionAttribute.ROLE, UserRole.GUEST.getValue());
        return page;
	}
}
