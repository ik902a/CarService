package by.epam.learn.controller.command.impl;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.learn.controller.command.Command;
import by.epam.learn.controller.command.PagePath;
import by.epam.learn.controller.command.Router;
import by.epam.learn.controller.command.SessionAttribute;
import by.epam.learn.controller.command.Router.RouteType;
import by.epam.learn.entity.Order;
import by.epam.learn.entity.User;
import by.epam.learn.exception.ServiceException;

import by.epam.learn.model.service.impl.OrderServiceImpl;

public class UpdateActiveOrderStatusCommand implements Command {
	public static Logger log = LogManager.getLogger();
    private final OrderServiceImpl service;

    public UpdateActiveOrderStatusCommand(OrderServiceImpl service) {
        this.service = service;
    }

	@Override
	public Router execute(HttpServletRequest request) {
		Router router;
		User mechanic = (User) request.getSession().getAttribute(SessionAttribute.MECHANIC);
		Order order = (Order) request.getSession().getAttribute(SessionAttribute.ORDER);
		boolean isChanged;
		try {
			isChanged = service.updateActiveStatus(order, mechanic);
			if (isChanged) {
				router = new Router(PagePath.PROFILE_REDIRECT, RouteType.REDIRECT);
			} else {
				router = new Router(PagePath.ERROR, RouteType.REDIRECT);
			}
        
		} catch (ServiceException e) {
			log.error("Exception while finding mechanic", e);
			router = new Router(PagePath.ERROR, RouteType.REDIRECT);
		}
		return router;
	}

}
