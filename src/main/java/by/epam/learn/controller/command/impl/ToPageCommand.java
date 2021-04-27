package by.epam.learn.controller.command.impl;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.learn.controller.command.Command;

public class ToPageCommand implements Command {
	public static Logger log = LogManager.getLogger();

    private final String page;

    public ToPageCommand(String page) {
        this.page = page;
    }

	@Override
	public String execute(HttpServletRequest request) {
        log.debug("Forward to {}", page);
        return page;
	}
}
