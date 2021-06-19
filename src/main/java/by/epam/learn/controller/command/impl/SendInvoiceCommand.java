package by.epam.learn.controller.command.impl;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.learn.controller.command.Command;
import by.epam.learn.controller.command.MessageKey;
import by.epam.learn.controller.command.PagePath;
import by.epam.learn.controller.command.RequestParameter;
import by.epam.learn.controller.command.Router;
import by.epam.learn.controller.command.AttributeParameter;
import by.epam.learn.controller.command.Router.RouteType;
import by.epam.learn.entity.Order;
import by.epam.learn.exception.ServiceException;
import by.epam.learn.model.service.impl.InvoiceServiceImpl;

/**
 * The {@code SendInvoiceCommand} class sends invoice to email
 * 
 * @author Ihar Klepcha
 */
public class SendInvoiceCommand implements Command {
	public static Logger log = LogManager.getLogger();
    private final InvoiceServiceImpl service;

    public SendInvoiceCommand(InvoiceServiceImpl service) {
        this.service = service;
    }

	@Override
	public Router execute(HttpServletRequest request) {
		Router router;
		Order order = (Order) request.getSession().getAttribute(RequestParameter.ORDER);
		if (request.getParameterValues(RequestParameter.OPERATION) != null) {
			String[] operations = request.getParameterValues(RequestParameter.OPERATION);
			try {
				boolean isAdded = service.addInvoice(operations, order);
				if (isAdded) {
					router = new Router(PagePath.PROFILE_REDIRECT, RouteType.REDIRECT);
				} else {
					log.error("Exception while adding");
					router = new Router(PagePath.INVOICE, RouteType.FORWARD);
				}
			} catch (ServiceException e) {
				request.setAttribute(AttributeParameter.EXCEPTION, e);
				log.error("Exception while adding", e);
				router = new Router(PagePath.ERROR, RouteType.FORWARD);
			}
		} else {
			request.setAttribute(AttributeParameter.ERROR_MESSAGE, MessageKey.MAKE_CHOICE_MESSAGE);
			router = new Router(PagePath.TO_INVOICE_REDIRECT, RouteType.FORWARD);
		}
		return router;
	}
}
