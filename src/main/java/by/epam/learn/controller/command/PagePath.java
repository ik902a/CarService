package by.epam.learn.controller.command;

/**
 * The {@code PagePath} contains all paths to pages and redirect paths
 * 
 * @author Ihar Klepcha
 */
public final class PagePath {
    public static final String HOME = "index.jsp";
    public static final String MAIN = "pages/main.jsp";
    public static final String SIGNUP = "pages/signup.jsp";
    public static final String LOGIN = "pages/login.jsp";
    public static final String ERROR = "pages/error.jsp";
    //Admin
    public static final String ADMIN_PROFILE = "pages/admin/admin_profile.jsp";
    public static final String ADD_USER = "pages/admin/add_user.jsp";
    public static final String EDIT_USER = "pages/admin/edit_user.jsp";
    public static final String PAGE_PRICE = "pages/admin/page_price.jsp";
    public static final String ADD_PRICE = "pages/admin/add_price.jsp";
    public static final String EDIT_PRICE = "pages/admin/edit_price.jsp";
    //Manager
    public static final String MANAGER_PROFILE = "pages/manager/manager_profile.jsp";
    public static final String INVOICE = "pages/manager/invoice.jsp";
    //Mechanic
    public static final String MECHANIC_PROFILE = "pages/mechanic/mechanic_profile.jsp";
    //Client
    public static final String CLIENT_PROFILE = "pages/client/client_profile.jsp";
    public static final String EDIT_CLIENT_PROFILE = "pages/client/edit_client_profile.jsp";
    public static final String ADD_CAR = "pages/client/add_new_car.jsp";
    //Redirect    
    public static final String HOME_REDIRECT = "controller?command=to_home";
    public static final String MAIN_REDIRECT = "controller?command=to_main";
	public static final String PROFILE_REDIRECT = "controller?command=to_profile";
	public static final String ADD_PRICE_REDIRECT = "controller?command=to_add_price";
	public static final String TO_EDIT_CLIENT_REDIRECT = "controller?command=to_edit_client";
	public static final String TO_INVOICE_REDIRECT = "controller?command=to_invoice";

    private PagePath() {
	}
}
