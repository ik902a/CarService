package by.epam.learn.controller.command.impl;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.learn.controller.command.Command;
import by.epam.learn.controller.command.PagePath;
import by.epam.learn.controller.command.SessionAttribute;
import by.epam.learn.entity.User;
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
	public String execute(HttpServletRequest request) {
        String page;
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);
        Optional<User> optionalUser = Optional.empty();
        try {
            optionalUser = service.login(login, password);
        } catch (ServiceException e) {
            log.error("Exception while logging in", e);
            request.setAttribute("ErrorMessage", "Exception while logging in: " + e.getMessage());
        }
        User user;
        if(optionalUser.isPresent()) {
            user = optionalUser.get();
            String role = user.getRole(); 
            request.getSession().setAttribute(SessionAttribute.ROLE, role);
            
            page = PagePath.MAIN;
        } else {
            request.setAttribute("errorUserMessage", "Incorrect login or password");
            page = PagePath.LOGIN;
        }
        return page;
	}
}
