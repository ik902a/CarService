package by.epam.learn.controller.command;

import by.epam.learn.controller.command.impl.*;
import by.epam.learn.model.service.impl.CarServiceImpl;
import by.epam.learn.model.service.impl.WorkTypeServiceImpl;
import by.epam.learn.model.service.impl.UserServiceImpl;
import by.epam.learn.model.service.impl.OrderServiceImpl;

public enum CommandType {
	TO_HOME(new ToPageCommand(PagePath.HOME)),
	TO_MAIN(new ToPageCommand(PagePath.MAIN)),
	TO_SIGNUP(new ToPageCommand(PagePath.SIGNUP)),
	SIGNUP(new SignUpCommand(new UserServiceImpl())),
	TO_LOGIN(new ToPageCommand(PagePath.LOGIN)),
    LOGIN(new LogInCommand(new UserServiceImpl())),
	LOGOUT(new LogOutCommand()),
	
	TO_PROFILE(new ToProfileCommand(new CarServiceImpl(), 
			new WorkTypeServiceImpl(), 
			new OrderServiceImpl(), 
			new UserServiceImpl())),
	
	TO_ADD_CAR(new ToPageCommand(PagePath.ADD_CAR)),
	ADD_CAR(new AddCarCommand(new CarServiceImpl())),
	TO_EDIT_PROFILE(new ToEditProfileCommand()),
	ADD_ORDER(new AddOrderCommand(new OrderServiceImpl())),
	UPDATE_CLIENT(new UpdateClientCommand(new UserServiceImpl())),
	
	UPDATE_ACTIVE_ORDER_STATUS(new UpdateActiveOrderStatusCommand(new OrderServiceImpl())),
	UPDATE_READY_ORDER_STATUS(new UpdateReadyOrderStatusCommand(new OrderServiceImpl())),
	
	ACTIVATION(new ActivationCommand(new UserServiceImpl())),
	CHANGE_LOCALE(new ChangeLocaleCommand());
    
//    SEARCH(new SearchCommand(new UserServiceImpl()));

    private final Command command;
    
    CommandType(Command command) {
    	this.command = command;
    }

	public Command getCommand(){
        return command;
    }
}
