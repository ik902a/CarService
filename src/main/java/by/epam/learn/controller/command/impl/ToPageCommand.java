package by.epam.learn.controller.command.impl;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.learn.controller.command.Command;
import by.epam.learn.controller.command.Router;
import by.epam.learn.controller.command.Router.RouteType;

/**
 * The {@code ToPageCommand} class represents go to the specified page
 * 
 * @author Ihar Klepcha
 */
public class ToPageCommand implements Command {
	public static Logger log = LogManager.getLogger();
    private final String page;

    public ToPageCommand(String page) {
        this.page = page;
    }

	@Override
	public Router execute(HttpServletRequest request) {
        log.debug("Forward to {}", page);
        Router router = new Router(page, RouteType.FORWARD);
        return router;
	}
}
