package by.epam.learn.controller.command;

import by.epam.learn.controller.command.impl.*;
import by.epam.learn.model.service.impl.CarServiceImpl;
import by.epam.learn.model.service.impl.WorkTypeServiceImpl;
import by.epam.learn.model.service.impl.UserServiceImpl;
import by.epam.learn.model.service.impl.OrderServiceImpl;
import by.epam.learn.model.service.impl.PriceServiceImpl;
import by.epam.learn.model.service.impl.InvoiceServiceImpl;

/**
 * The {@code CommandType} contains all commands
 * 
 * @author Ihar Klepcha
 */
public enum CommandType {
	TO_HOME(new ToPageCommand(PagePath.HOME)),
	TO_MAIN(new ToMainCommand(new PriceServiceImpl())),
	TO_SIGNUP(new ToPageCommand(PagePath.SIGNUP)),
	SIGNUP(new SignUpCommand(new UserServiceImpl())),
	TO_LOGIN(new ToPageCommand(PagePath.LOGIN)),
    LOGIN(new LogInCommand(new UserServiceImpl())),
	LOGOUT(new LogOutCommand()),
	TO_PROFILE(new ToProfileCommand(new CarServiceImpl(), 
			new WorkTypeServiceImpl(), 
			new OrderServiceImpl(), 
			new UserServiceImpl())),
	TO_ADD_USER(new ToPageCommand(PagePath.ADD_USER)),
	SEARCH_USER(new SearchUserCommand(new UserServiceImpl())),
	ADD_USER(new AddUserCommand(new UserServiceImpl())),
	TO_EDIT_USER(new ToEditUserCommand(new UserServiceImpl())),
	UPDATE_USER(new UpdateUserCommand(new UserServiceImpl())),
	DELETE_USER(new DeleteUserCommand(new UserServiceImpl())),
	TO_PAGE_PRICE(new ToPagePriceCommand(new PriceServiceImpl())),
	SEARCH_PRICE(new SearchPriceCommand(new PriceServiceImpl())),
	TO_ADD_PRICE(new ToAddPriceCommand(new WorkTypeServiceImpl())),
	ADD_PRICE(new AddPriceCommand(new PriceServiceImpl())),
	TO_EDIT_PRICE(new ToEditPriceCommand(new PriceServiceImpl())),
	UPDATE_PRICE(new UpdatePriceCommand(new PriceServiceImpl())),
	DELETE_PRICE(new DeletePriceCommand(new PriceServiceImpl())),
	ADD_ORDER(new AddOrderCommand(new OrderServiceImpl())),
	TO_ADD_CAR(new ToPageCommand(PagePath.ADD_CAR)),
	ADD_CAR(new AddCarCommand(new CarServiceImpl())),
	TO_EDIT_CLIENT(new ToEditClientCommand()),
	UPDATE_CLIENT(new UpdateClientCommand(new UserServiceImpl())),
	UPDATE_ACTIVE_ORDER_STATUS(new UpdateActiveOrderStatusCommand(new OrderServiceImpl())),
	UPDATE_READY_ORDER_STATUS(new UpdateReadyOrderStatusCommand(new OrderServiceImpl())),
	TO_INVOICE(new ToInvoiceCommand(new OrderServiceImpl(), new PriceServiceImpl())),
	SEND_INVOICE(new SendInvoiceCommand(new InvoiceServiceImpl())),
	ACTIVATION(new ActivationCommand(new UserServiceImpl())),
	CHANGE_LOCALE(new ChangeLocaleCommand());

    private final Command command;
    
    CommandType(Command command) {
    	this.command = command;
    }

	public Command getCommand(){
        return command;
    }
}
