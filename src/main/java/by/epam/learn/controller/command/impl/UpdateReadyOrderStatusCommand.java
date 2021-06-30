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
import by.epam.learn.entity.Order;
import by.epam.learn.exception.ServiceException;
import by.epam.learn.model.service.impl.OrderServiceImpl;

/**
 * The {@code UpdateReadyOrderStatusCommand} class changes order status
 * 
 * @author Ihar Klepcha
 */
public class UpdateReadyOrderStatusCommand implements Command {
	public static Logger log = LogManager.getLogger();
    private final OrderServiceImpl service;

    public UpdateReadyOrderStatusCommand(OrderServiceImpl service) {
        this.service = service;
    }

	@Override
	public Router execute(HttpServletRequest request) {
		Router router;
		String orderId = request.getParameter(RequestParameter.ORDER_ID);
		try {
			Order order = service.findOrderById(orderId);
			boolean isChanged = service.updateReadyStatus(order);
			if (isChanged) {
			router = new Router(PagePath.PROFILE_REDIRECT, RouteType.REDIRECT);
			} else {
				router = new Router(PagePath.ERROR, RouteType.REDIRECT);
			}
		} catch (ServiceException e) {
			request.setAttribute(AttributeParameter.EXCEPTION, e);
			log.error("exception while updating order", e);
			router = new Router(PagePath.ERROR, RouteType.FORWARD);
		}
		return router;
	}
}
