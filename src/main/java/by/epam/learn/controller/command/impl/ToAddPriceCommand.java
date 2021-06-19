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
import by.epam.learn.entity.WorkType;
import by.epam.learn.exception.ServiceException;
import by.epam.learn.model.service.impl.WorkTypeServiceImpl;

/**
 * The {@code ToAddPriceCommand} class represents go to add price page
 * 
 * @author Ihar Klepcha
 */
public class ToAddPriceCommand implements Command {
	public static Logger log = LogManager.getLogger();
    private final WorkTypeServiceImpl service;

    public ToAddPriceCommand(WorkTypeServiceImpl service) {
        this.service = service;
    }

	@Override
	public Router execute(HttpServletRequest request) {
		Router router;
		try {
			List<WorkType> works = service.findAllWorkType();
			if (!works.isEmpty()) {
				request.setAttribute(AttributeParameter.WORK_LIST, works);
			}
			router = new Router(PagePath.ADD_PRICE, RouteType.FORWARD);
		} catch (ServiceException e) {
			request.setAttribute(AttributeParameter.EXCEPTION, e);
			log.error("exception while finding type of works", e);
			router = new Router(PagePath.ERROR, RouteType.FORWARD);
		}
		return router;
	}
}
