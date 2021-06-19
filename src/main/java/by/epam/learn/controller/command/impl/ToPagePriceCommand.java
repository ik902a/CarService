package by.epam.learn.controller.command.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.learn.controller.command.Command;
import by.epam.learn.controller.command.PagePath;
import by.epam.learn.controller.command.Router;
import by.epam.learn.controller.command.Router.RouteType;
import by.epam.learn.controller.command.AttributeParameter;
import by.epam.learn.entity.Price;
import by.epam.learn.exception.ServiceException;
import by.epam.learn.model.service.impl.PriceServiceImpl;

/**
 * The {@code ToPagePriceCommand} class represents go to page with prices
 * 
 * @author Ihar Klepcha
 */
public class ToPagePriceCommand implements Command {
	public static Logger log = LogManager.getLogger();
	private final PriceServiceImpl service;
	
	public ToPagePriceCommand(PriceServiceImpl service) {
		this.service = service;
	}

	@Override
	public Router execute(HttpServletRequest request) {
		Router router;
		try {
			List<Price> prices = service.findAll();
			if (!prices.isEmpty()) {
				request.setAttribute(AttributeParameter.PRICE_LIST, prices);
			}
			router = new Router(PagePath.PAGE_PRICE, RouteType.FORWARD);
		} catch (ServiceException e) {
			request.setAttribute(AttributeParameter.EXCEPTION, e);
			log.error("exception while finding prices", e);
			router = new Router(PagePath.ERROR, RouteType.FORWARD);
		}
		return router;
	}
}
