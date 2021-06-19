package by.epam.learn.controller.command.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.learn.controller.command.Command;
import by.epam.learn.controller.command.PagePath;
import by.epam.learn.controller.command.RequestParameter;
import by.epam.learn.controller.command.Router;
import by.epam.learn.controller.command.AttributeParameter;
import by.epam.learn.controller.command.Router.RouteType;
import by.epam.learn.entity.Order;
import by.epam.learn.entity.Price;
import by.epam.learn.exception.ServiceException;
import by.epam.learn.model.service.impl.OrderServiceImpl;
import by.epam.learn.model.service.impl.PriceServiceImpl;

/**
 * The {@code ToInvoiceCommand} class represents go to invoices page
 * 
 * @author Ihar Klepcha
 */
public class ToInvoiceCommand implements Command {
	public static Logger log = LogManager.getLogger();
    private final OrderServiceImpl orderService;
	private final PriceServiceImpl priceService;

	public ToInvoiceCommand(OrderServiceImpl orderService, PriceServiceImpl priceService) {
		this.orderService = orderService;
		this.priceService = priceService;
	}

	@Override
	public Router execute(HttpServletRequest request) {
		Router router;
		Order order = (Order) request.getSession().getAttribute(RequestParameter.ORDER);
		try {
			boolean isChanged = orderService.updateCompletedStatus(order);
			List<Price> prices = priceService.findPriceByWorkType(order);
			if (!prices.isEmpty() && isChanged) {
				request.setAttribute(AttributeParameter.PRICE_LIST, prices);
			}
			router = new Router(PagePath.INVOICE, RouteType.FORWARD);
		} catch (ServiceException e) {
			request.setAttribute(AttributeParameter.EXCEPTION, e);
			log.error("exception while finding price", e);
			router = new Router(PagePath.ERROR, RouteType.FORWARD);
		}
		return router;
	}
}
