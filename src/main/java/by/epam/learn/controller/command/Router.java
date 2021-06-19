package by.epam.learn.controller.command;

/**
 * The {@code Router} class incapsulate page which user will see and redirection 
 * 
 * @author Ihar Klepcha
 */
public class Router {
	
	/**
	 * The {@code RouteType} enum contains all route types
	 * 
	 * @author Ihar Klepcha
	 */
	public enum RouteType {
		FORWARD, REDIRECT
	}
	
	private final String pagePath;
	private final RouteType routeType;

	/**
	 * Constructs a new Router with the specified page path and route type
	 * 
	 * @param pagePath  {@link String} path to page
	 * @param routeType {@link RouteType} route type
	 */
	public Router(String pagePath, RouteType routeType) {
		this.pagePath = pagePath;
		this.routeType = routeType;
	}

	public String getPagePath() {
		return pagePath;
	}

	public RouteType getRouteType() {
		return routeType;
	}
}
