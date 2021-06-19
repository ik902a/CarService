package by.epam.learn.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.learn.controller.command.Command;
import by.epam.learn.controller.command.CommandProvider;
import by.epam.learn.controller.command.PagePath;
import by.epam.learn.controller.command.Router;
import by.epam.learn.exception.ConnectionPoolException;
import by.epam.learn.model.pool.ConnectionPool;

/**
 * The {@code Controller} class receives request from client (get or post)
 * 
 * @author Ihar Klepcha
 * @see HttpServlet
 */
@WebServlet(name = "controller", urlPatterns = { "/controller" })
public class Controller extends HttpServlet {
	public static Logger log = LogManager.getLogger();
	private static final long serialVersionUID = 1L;
	private static final String COMMAND = "command";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	/**
	 * Processes get and post requests
	 * 
	 * @param request  {@link HttpServletRequest}
	 * @param response {@link HttpServletResponse}
	 * @throws ServletException
	 * @throws IOException
	 */
	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String commandName = request.getParameter(COMMAND);
		log.debug("Controller command=" + commandName);
		Command command = CommandProvider.defineCommand(commandName);
		Router router = command.execute(request);
		switch (router.getRouteType()) {
		case REDIRECT:
			log.debug("Controller redirect " + router.getPagePath());
			response.sendRedirect(router.getPagePath());
			break;
		case FORWARD:
			log.debug("Controller forward " + router.getPagePath());
			RequestDispatcher dispatcher = request.getRequestDispatcher(router.getPagePath());
			dispatcher.forward(request, response);
			break;
		default:
			log.error("imposible route type");
			response.sendRedirect(PagePath.ERROR);
		}
	}
	
	/**
	 * Destroys pool
	 * 
	 * @throws ConnectionPoolException
	 */
    @Override
    public void destroy() {
        try {
            ConnectionPool.getInstance().destroyPool();
        } catch (ConnectionPoolException e) {
            log.error("Exception while destroying the connection pool", e);
        }
        log.info("Connection pool destroyed successfully");
    }
}
