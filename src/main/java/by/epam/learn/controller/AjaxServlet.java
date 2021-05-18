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

@WebServlet(name = "/AjaxServlet", urlPatterns = "/view")
public class AjaxServlet extends HttpServlet {
	public static Logger log = LogManager.getLogger();
	private static final long serialVersionUID = 1L;
	private static final String COMMAND_VIEW = "view";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		log.info("ajax servlet");
		String commandName = request.getParameter(COMMAND_VIEW);
		Command command = CommandProvider.defineCommand(commandName);
		
		command.execute((HttpServletRequest) request);
		
		// TODO Auto-generated method stub
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}
}
