package by.epam.learn.controller.command.impl;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.learn.controller.command.AttributeParameter;
import by.epam.learn.controller.command.Command;
import by.epam.learn.controller.command.PagePath;
import by.epam.learn.controller.command.RequestParameter;
import by.epam.learn.controller.command.Router;
import by.epam.learn.controller.command.Router.RouteType;
import by.epam.learn.exception.ServiceException;
import by.epam.learn.model.service.impl.PriceServiceImpl;

/**
 * The {@code DeletePriceCommand} class deletes price
 * 
 * @author Ihar Klepcha
 */
public class DeletePriceCommand implements Command {
	public static Logger log = LogManager.getLogger();
    private final PriceServiceImpl service;

    public DeletePriceCommand(PriceServiceImpl service) {
        this.service = service;
    }

	@Override
	public Router execute(HttpServletRequest request) {
		Router router;
		String priceIdValue = request.getParameter(RequestParameter.ID);
		try {
			boolean isDeleted = service.delete(priceIdValue);
			if (isDeleted) {
				router = new Router(PagePath.PROFILE_REDIRECT, RouteType.REDIRECT);
			} else {
				router = new Router(PagePath.ERROR, RouteType.REDIRECT);
			}
		} catch (ServiceException e) {
			request.setAttribute(AttributeParameter.EXCEPTION, e);
			log.error("exception while deleting price", e);
			router = new Router(PagePath.ERROR, RouteType.FORWARD);
		}
		return router;
	}
}
