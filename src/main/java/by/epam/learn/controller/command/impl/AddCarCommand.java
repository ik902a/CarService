package by.epam.learn.controller.command.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.learn.controller.command.Command;
import by.epam.learn.controller.command.PagePath;
import by.epam.learn.exception.ServiceException;
import by.epam.learn.model.service.impl.CarServiceImpl;
import by.epam.learn.model.service.impl.UserServiceImpl;

import static by.epam.learn.controller.command.RequestParameter.*;
import static by.epam.learn.controller.command.DataKeyword.*;

public class AddCarCommand implements Command {
	public static Logger log = LogManager.getLogger();
    private final CarServiceImpl service;

    public AddCarCommand(CarServiceImpl service) {
        this.service = service;
    }

	@Override
	public String execute(HttpServletRequest request) {
        String page;
        String vinValue = request.getParameter(VIN);
        String brandValue = request.getParameter(BRAND);
        String modelValue = request.getParameter(MODEL);
        String yearValue = request.getParameter(YEAR);
        String fuelValue = request.getParameter(FUEL);
        String volumeValue = request.getParameter(VOLUME);
        String transmissionValue = request.getParameter(TRANSMISSION);
        

        Map<String, String> userData = new HashMap<>();
        userData.put(VIN_KEY, vinValue);
        userData.put(BRAND_KEY, brandValue);
        userData.put(MODEL_KEY, modelValue);
        userData.put(YEAR_KEY, yearValue);
        userData.put(FUEL_KEY, fuelValue);
        userData.put(VOLUME_KEY, volumeValue);
        userData.put(TRANSMISSION_KEY, transmissionValue);
        boolean isSignUp;
        try {
            isSignUp = service.addCar(userData);
           
            
            
//            if (isSignUp) {
//                page = PagePath.HOME;
//            } else {//TODO
//            	loginValue = userData.get(LOGIN_KEY);
//                emailValue = userData.get(EMAIL_KEY);
//                passValue = userData.get(PASSWORD_KEY);
//                passConfirmValue = userData.get(CONFIRMING_PASSWORD_KEY);
//                if (loginValue.contains(INCORRECT_VALUE)) {
//                    // TODO: include properties
//                    request.setAttribute("errorLoginMessage", "Incorrect email");
//                }
//                if (emailValue.contains(INCORRECT_VALUE)) {
//                    // TODO: include properties
//                    request.setAttribute("errorEmailMessage", "Incorrect email");
//                }
//                if (passValue.contains(INCORRECT_VALUE)) {
//                    // TODO: include properties
//                    request.setAttribute("errorPasswordMessage", "Incorrect password. It must include at least one letter in upper and in lower case, at least one cipher, at least one special character (\"@\", \"#\". \"$\", \"%\", \"^\", \"&\", \"(\" or \")\", no spaces and have from 8 to 20 characters");
//                }
//                if (passConfirmValue.contains(DOESNT_MATCH)) {
//                    // TODO: include properties
//                    request.setAttribute("errorPasswordConfirmingMessage", "Doesn't match");
//                }
//                page = PagePath.SIGNUP;
//            }
//        } catch (ServiceException e) {
//            log.error("Exception while signing up", e);
//            page = PagePath.ERROR;
//        }
        return page;
	}

}
