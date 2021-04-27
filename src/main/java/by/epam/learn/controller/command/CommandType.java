package by.epam.learn.controller.command;

import by.epam.learn.controller.command.impl.SignUpCommand;
import by.epam.learn.controller.command.impl.ToPageCommand;
import by.epam.learn.controller.command.impl.LogInCommand;
import by.epam.learn.controller.command.impl.LogOutCommand;
import by.epam.learn.controller.command.impl.ToProfileCommand;
import by.epam.learn.controller.command.impl.AddCarCommand;
import by.epam.learn.controller.command.impl.ChangeLocaleCommand;

import by.epam.learn.model.service.impl.UserServiceImpl;

public enum CommandType {
	SIGNUP(new SignUpCommand(new UserServiceImpl())),
	TO_MAIN(new ToPageCommand(PagePath.MAIN)),
	TO_SIGNUP(new ToPageCommand(PagePath.SIGNUP)),
    LOGIN(new LogInCommand(new UserServiceImpl())),
	TO_LOGIN(new ToPageCommand(PagePath.LOGIN)),
	LOGOUT(new LogOutCommand()),
	TO_PROFILE(new ToProfileCommand()),
	ADD_CAR(new AddCarCommand(new CarServiceImpl())),
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
