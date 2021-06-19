package by.epam.learn.controller.command.impl;

import java.util.List;
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
import by.epam.learn.entity.Order;
import by.epam.learn.entity.User;
import by.epam.learn.entity.UserRole;
import by.epam.learn.entity.WorkType;
import by.epam.learn.exception.ServiceException;
import by.epam.learn.model.service.impl.CarServiceImpl;
import by.epam.learn.model.service.impl.OrderServiceImpl;
import by.epam.learn.model.service.impl.UserServiceImpl;
import by.epam.learn.model.service.impl.WorkTypeServiceImpl;

/**
 * The {@code ToProfileCommand} class represents go to profile page for admin,
 * manager, mechanic or client
 * 
 * @author Ihar Klepcha
 */
public class ToProfileCommand implements Command {
	public static Logger log = LogManager.getLogger();
	private final CarServiceImpl carService;
	private final WorkTypeServiceImpl workTypeService;
	private final OrderServiceImpl orderService;
	private final UserServiceImpl userService;

	public ToProfileCommand(CarServiceImpl carService, WorkTypeServiceImpl workTypeService,
			OrderServiceImpl orderService, UserServiceImpl userService) {
		this.carService = carService;
		this.workTypeService = workTypeService;
		this.orderService = orderService;
		this.userService = userService;
	}

	@Override
	public Router execute(HttpServletRequest request) {
		Router router;
		UserRole currentRole = (UserRole) request.getSession().getAttribute(RequestParameter.ROLE);
		try {
			switch (currentRole) {
			case ADMIN:
				List<User> users = userService.findAll();
				if (!users.isEmpty()) {
					request.setAttribute(AttributeParameter.USER_LIST, users);
				}
				router = new Router(PagePath.ADMIN_PROFILE, RouteType.FORWARD);
				break;
			case MANAGER:
				List<Order> managerOrders = orderService.findAllOrder();
				if (!managerOrders.isEmpty()) {
					request.setAttribute(AttributeParameter.ORDER_LIST, managerOrders);
				}
				List<User> mechanics = userService.findMechanic();
				if (!mechanics.isEmpty()) {
					request.setAttribute(AttributeParameter.MECHANIC_LIST, mechanics);
				}
				router = new Router(PagePath.MANAGER_PROFILE, RouteType.FORWARD);
				break;
			case MECHANIC:
				User mechanic = (User) request.getSession().getAttribute(RequestParameter.USER);
				List<Order> mechanicOrders = orderService.findOrderByMechanic(mechanic);
				if (!mechanicOrders.isEmpty()) {
					request.setAttribute(AttributeParameter.ORDER_LIST, mechanicOrders);
				}
				router = new Router(PagePath.MECHANIC_PROFILE, RouteType.FORWARD);
				break;
			case CLIENT:
				User client = (User) request.getSession().getAttribute(AttributeParameter.USER);
				Map<Long, String> cars = carService.findCar(client);
				if (!cars.isEmpty()) {
					request.setAttribute(AttributeParameter.CAR_LIST, cars);
				} else {
					request.setAttribute(AttributeParameter.INFO_MESSAGE, MessageKey.ADD_CAR_MESSAGE);
				}
				List<WorkType> works = workTypeService.findAllWorkType();
				if (!works.isEmpty()) {
					request.setAttribute(AttributeParameter.WORK_LIST, works);
				}
				router = new Router(PagePath.CLIENT_PROFILE, RouteType.FORWARD);
				break;
			default:
				log.error("incorrect role type");
				router = new Router(PagePath.HOME, RouteType.REDIRECT);
			}
		} catch (ServiceException e) {
			request.setAttribute(AttributeParameter.EXCEPTION, e);
			log.error("exception while finding for profile page", e);
			router = new Router(PagePath.ERROR, RouteType.FORWARD);
		}
		return router;
	}
}
