package by.epam.learn.controller.command.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.learn.controller.command.Command;
import by.epam.learn.controller.command.MessageKey;
import by.epam.learn.controller.command.PagePath;
import by.epam.learn.controller.command.RequestParameter;
import by.epam.learn.controller.command.Router;
import by.epam.learn.controller.command.Router.RouteType;
import by.epam.learn.controller.command.AttributeParameter;
import by.epam.learn.entity.Price;
import by.epam.learn.exception.ServiceException;
import by.epam.learn.model.service.impl.PriceServiceImpl;

import static by.epam.learn.controller.command.DataKeyword.*;

/**
 * The {@code UpdatePriceCommand} class updates price
 * 
 * @author Ihar Klepcha
 */
public class UpdatePriceCommand implements Command {
	public static Logger log = LogManager.getLogger();
    private final PriceServiceImpl service;

    public UpdatePriceCommand(PriceServiceImpl service) {
        this.service = service;
    }

	@Override
	public Router execute(HttpServletRequest request) {
		Router router;
		String priceIdValue = request.getParameter(RequestParameter.ID);
		String priceValue = request.getParameter(RequestParameter.PRICE);
		Map<String, String> priceData = new HashMap<>();
		priceData.put(ID_KEY, priceIdValue);
		priceData.put(PRICE_KEY, priceValue);
		try {
			boolean isUpdate = service.updatePrice(priceData);
			if (isUpdate) {
				router = new Router(PagePath.PROFILE_REDIRECT, RouteType.REDIRECT);	
			} else {	
				priceValue = priceData.get(PRICE_KEY);
				if (priceValue.contains(INCORRECT_VALUE)) {
					request.setAttribute(AttributeParameter.ERROR_MESSAGE, MessageKey.INCORRECT_PRICE_MESSAGE);
				}
				Price priceList = service.findPriceById(priceIdValue);
				request.setAttribute(RequestParameter.PRICE, priceList);
				router = new Router(PagePath.EDIT_PRICE, RouteType.FORWARD);
			}
		} catch (ServiceException e) {
			request.setAttribute(AttributeParameter.EXCEPTION, e);
			log.error("exception while update price", e);
			router = new Router(PagePath.ERROR, RouteType.FORWARD);
		}
		return router;
	}
}
