package by.epam.learn.controller.command.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.learn.controller.command.Command;
import by.epam.learn.controller.command.MessageKey;
import by.epam.learn.controller.command.PagePath;
import by.epam.learn.controller.command.Router;
import by.epam.learn.controller.command.Router.RouteType;
import by.epam.learn.exception.ServiceException;
import by.epam.learn.model.service.impl.OrderServiceImpl;

import static by.epam.learn.controller.command.RequestParameter.*;
import static by.epam.learn.controller.command.DataKeyword.*;

public class AddOrderCommand implements Command {
	public static Logger log = LogManager.getLogger();
    private final OrderServiceImpl service;

    public AddOrderCommand(OrderServiceImpl service) {
        this.service = service;
    }

	@Override
	public Router execute(HttpServletRequest request) {
		List<String> errorMessageList = new ArrayList<>();
		Router router;
		log.debug(request.getParameter("carOrder"));
		String carIdValue = request.getParameter(CAR_ORDER);
		log.debug(request.getParameter("workOrder"));
		String workIdValue = request.getParameter(WORK_ORDER);
		String messageValue = request.getParameter(MESSAGE_ORDER);

		Map<String, String> orderData = new HashMap<>();
		orderData.put(CAR_ID_KEY, carIdValue);
		orderData.put(WORK_ID_KEY, workIdValue);
		orderData.put(MESSAGE_KEY, messageValue);
		boolean isAdded;
		try {
			isAdded = service.addOrder(orderData);
			if (isAdded) {
				router = new Router(PagePath.CLIENT_PROFILE, RouteType.REDIRECT);
			} else {// TODO
				carIdValue = orderData.get(CAR_ID_KEY);
				workIdValue = orderData.get(WORK_ID_KEY);
				messageValue = orderData.get(MESSAGE_KEY);
				if (carIdValue == null) {
					errorMessageList.add(MessageKey.SELECT_CAR_MESSAGE);
				}
				if (workIdValue == null) {
					errorMessageList.add(MessageKey.SELECT_WORK_MESSAGE);
				}
				if (messageValue.contains(INCORRECT_VALUE)) {
					errorMessageList.add(MessageKey.INCORRECT_SYMBOL_MESSAGE);
				}
				request.setAttribute(ERROR_MESSAGE_LIST, errorMessageList);
				router = new Router(PagePath.CLIENT_PROFILE, RouteType.FORWARD);//TODO
			}
		} catch (ServiceException e) {
			log.error("Exception while adding", e);
			router = new Router(PagePath.ERROR, RouteType.REDIRECT);
		}
		return router;
	}
}
