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
import by.epam.learn.controller.command.AttributeParameter;
import by.epam.learn.exception.ServiceException;
import by.epam.learn.model.service.impl.PriceServiceImpl;

import static by.epam.learn.controller.command.RequestParameter.*;
import static by.epam.learn.controller.command.DataKeyword.*;

/**
 * The {@code AddPriceCommand} class adds new price
 * 
 * @author Ihar Klepcha
 */
public class AddPriceCommand implements Command {
	public static Logger log = LogManager.getLogger();
    private final PriceServiceImpl service;

    public AddPriceCommand(PriceServiceImpl service) {
        this.service = service;
    }

	@Override
	public Router execute(HttpServletRequest request) {
		List<String> errorMessageList = new ArrayList<>();
		Router router;
		String operationValue = request.getParameter(OPERATION);
		String priceValue = request.getParameter(PRICE);
		String workTypeValue = request.getParameter(WORK_TYPE);
		Map<String, String> priceData = new HashMap<>();
		priceData.put(OPERATION_KEY, operationValue);
		priceData.put(PRICE_KEY, priceValue);
		priceData.put(WORK_TYPE_KEY, workTypeValue);
		try {
			boolean isAdded = service.addPrice(priceData);
			if (isAdded) {
				router = new Router(PagePath.ADD_PRICE_REDIRECT, RouteType.REDIRECT);
			} else {
				operationValue = priceData.get(OPERATION_KEY);
				priceValue = priceData.get(PRICE_KEY);
				workTypeValue = priceData.get(WORK_TYPE_KEY);
				if (operationValue.contains(INCORRECT_VALUE)) {
					errorMessageList.add(MessageKey.INCORRECT_OPERATION_MESSAGE);
				}
				if (priceValue.contains(INCORRECT_VALUE)) {
					errorMessageList.add(MessageKey.INCORRECT_PRICE_MESSAGE);
				}
				if (workTypeValue.contains(INCORRECT_VALUE)) {
					errorMessageList.add(MessageKey.SELECT_WORK_MESSAGE);
				}
				request.setAttribute(AttributeParameter.ERROR_MESSAGE_LIST, errorMessageList);
				router = new Router(PagePath.ADD_PRICE_REDIRECT, RouteType.FORWARD);
			}
		} catch (ServiceException e) {
			request.setAttribute(AttributeParameter.EXCEPTION, e);
			log.error("exception while adding price", e);
			router = new Router(PagePath.ERROR, RouteType.FORWARD);
		}
		return router;
	}
}
