package by.epam.learn.controller.command.impl;

import javax.servlet.http.HttpServletRequest;

import by.epam.learn.controller.command.Command;
import by.epam.learn.controller.command.PagePath;

public class UnknownCommand implements Command {

	@Override
	public String execute(HttpServletRequest request) {
		String page = PagePath.HOME;
		return page;
	}
}
