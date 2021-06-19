package by.epam.learn.controller.command;

import javax.servlet.http.HttpServletRequest;

/**
 * The {@code Command} interface, which represents Command pattern 
 * 
 * @author Ihar Klepcha
 */
public interface Command {
	
	/**
	 * Executes command
	 * 
	 * @param request {@link HttpServletRequest}
	 * @return {@link Router}
	 */
	Router execute(HttpServletRequest request);
}
