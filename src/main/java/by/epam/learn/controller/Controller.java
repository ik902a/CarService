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
import by.epam.learn.exception.ConnectionPoolException;
import by.epam.learn.model.pool.ConnectionPool;

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
	
	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String commandName = request.getParameter(COMMAND);
		log.debug("controller=" + commandName);
		String page;
		if (commandName != null) {
			Command command = CommandProvider.defineCommand(commandName);
			page = command.execute(request);
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
		} else {
            page = PagePath.HOME;
            response.sendRedirect(request.getContextPath() + page);
        }
	}
	
    @Override
    public void destroy() {
        try {
            ConnectionPool.getInstance().destroyPool();
        } catch (ConnectionPoolException e) {
            log.error("Exception while destroying the connection pool", e);
        }
        log.debug("Connection pool destroyed successfully");
    }
}
