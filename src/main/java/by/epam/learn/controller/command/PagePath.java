package by.epam.learn.controller.command;

public final class PagePath {
    public static final String HOME = "index.jsp";
    public static final String MAIN = "pages/main.jsp";
    public static final String SIGNUP = "pages/signup.jsp";
    public static final String LOGIN = "pages/login.jsp";
    
    public static final String ADMIN_PROFILE = "pages/admin/admin_profile.jsp";
    
    public static final String MANAGER_PROFILE = "pages/manager/manager_profile.jsp";
    public static final String MECHANIC_PROFILE = "pages/mechanic/mechanic_profile.jsp";
    
    public static final String CLIENT_PROFILE = "pages/client/client_profile.jsp";
    public static final String EDIT_CLIENT_PROFILE = "pages/client/edit_client_profile.jsp";
    public static final String ADD_CAR = "pages/client/add_new_car.jsp";
    
    public static final String ERROR = "pages/error.jsp";
    
    public static final String HOME_REDIRECT = "controller?command=to_home";
    public static final String MAIN_REDIRECT = "controller?command=to_main";
	public static final String PROFILE_REDIRECT = "controller?command=to_profile";

    private PagePath() {
	}
}
