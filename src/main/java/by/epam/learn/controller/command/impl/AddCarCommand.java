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
import by.epam.learn.controller.command.SessionAttribute;
import by.epam.learn.controller.command.Router.RouteType;
import by.epam.learn.entity.User;
import by.epam.learn.exception.ServiceException;
import by.epam.learn.model.service.impl.CarServiceImpl;

import static by.epam.learn.controller.command.RequestParameter.*;
import static by.epam.learn.controller.command.DataKeyword.*;

public class AddCarCommand implements Command {
	public static Logger log = LogManager.getLogger();
    private final CarServiceImpl service;

    public AddCarCommand(CarServiceImpl service) {
        this.service = service;
    }

	@Override
	public Router execute(HttpServletRequest request) {
		List<String> errorMessageList = new ArrayList<>();
        Router router;
        String vinValue = request.getParameter(VIN);
        String brandValue = request.getParameter(BRAND);
        String modelValue = request.getParameter(MODEL);
        String yearValue = request.getParameter(YEAR);
        String fuelValue = request.getParameter(FUEL);
        String volumeValue = request.getParameter(VOLUME);
        String transmissionValue = request.getParameter(TRANSMISSION);
        
        Map<String, String> carData = new HashMap<>();
        carData.put(VIN_KEY, vinValue);
        carData.put(BRAND_KEY, brandValue);
        carData.put(MODEL_KEY, modelValue);
        carData.put(YEAR_KEY, yearValue);
        carData.put(FUEL_KEY, fuelValue);
        carData.put(VOLUME_KEY, volumeValue);
        carData.put(TRANSMISSION_KEY, transmissionValue);
        
        User user = (User) request.getSession().getAttribute(SessionAttribute.USER);
        boolean isAdded;
        try {
            isAdded = service.addCar(carData, user);
            
            if (isAdded) {
            	router = new Router(PagePath.CLIENT_PROFILE, RouteType.REDIRECT);
            } else {
            	vinValue = carData.get(VIN_KEY);
                brandValue = carData.get(BRAND_KEY);
                modelValue = carData.get(MODEL_KEY);
                yearValue = carData.get(YEAR_KEY);
                fuelValue = carData.get(FUEL_KEY);
                volumeValue = carData.get(VOLUME_KEY);
                transmissionValue = carData.get(TRANSMISSION_KEY);
                
                if (vinValue.contains(INCORRECT_VALUE)) {
                	errorMessageList.add(MessageKey.INCORRECT_VIN_MESSAGE);
                }
                if (brandValue.contains(INCORRECT_VALUE)) {
                	errorMessageList.add(MessageKey.INCORRECT_BRAND_MESSAGE);
                }
                if (modelValue.contains(INCORRECT_VALUE)) {
                	errorMessageList.add(MessageKey.INCORRECT_MODEL_MESSAGE);
                }
                if (yearValue.contains(INCORRECT_VALUE)) {
                	errorMessageList.add(MessageKey.INCORRECT_YEAR_MESSAGE);
                }
                if (fuelValue.contains(INCORRECT_VALUE)) {
                	errorMessageList.add(MessageKey.INCORRECT_FUEL_MESSAGE);
                }
                if (volumeValue.contains(INCORRECT_VALUE)) {
                	errorMessageList.add(MessageKey.INCORRECT_VOLUME_MESSAGE);
                }
                if (transmissionValue.contains(INCORRECT_VALUE)) {
                	errorMessageList.add(MessageKey.INCORRECT_TRANSMISSION_MESSAGE);
                }
                request.setAttribute(ERROR_MESSAGE_LIST, errorMessageList);
                router = new Router(PagePath.ADD_CAR, RouteType.FORWARD);//TODO
            }
        } catch (ServiceException e) {
            log.error("Exception while adding", e);
            router = new Router(PagePath.ERROR, RouteType.REDIRECT);
        }
        return router;
	}
}
