package by.epam.learn.controller.command.impl;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.learn.controller.command.Command;
import by.epam.learn.controller.command.PagePath;
import by.epam.learn.controller.command.RequestParameter;
import by.epam.learn.controller.command.Router;
import by.epam.learn.controller.command.Router.RouteType;
import by.epam.learn.controller.command.AttributeParameter;
import by.epam.learn.entity.Price;
import by.epam.learn.exception.ServiceException;
import by.epam.learn.model.service.impl.PriceServiceImpl;

/**
 * The {@code ToEditPriceCommand} class represents go to edit price page
 * 
 * @author Ihar Klepcha
 */
public class ToEditPriceCommand implements Command {
	public static Logger log = LogManager.getLogger();
    private final PriceServiceImpl service;

    public ToEditPriceCommand(PriceServiceImpl service) {
        this.service = service;
    }

	@Override
	public Router execute(HttpServletRequest request) {
		Router router;
		Price price;
		String priceId = request.getParameter(RequestParameter.ID);
		try {
			price = service.findPriceById(priceId);
		} catch (ServiceException e) {
			price = new Price();
			request.setAttribute(AttributeParameter.EXCEPTION, e);
			log.error("exception while searching price", e);
			router = new Router(PagePath.ERROR, RouteType.FORWARD);
		}		
		request.setAttribute(AttributeParameter.PRICE, price);
        router = new Router(PagePath.EDIT_PRICE, RouteType.FORWARD);
		return router;
	}
}
