package by.epam.learn.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.learn.controller.command.impl.UnknownCommand;

public class CommandProvider {
	public static Logger log = LogManager.getLogger();
	
    private CommandProvider() {
    }
    
    public static Command defineCommand(String commandName) {
        Command currentCommand;
        if (commandName != null) {
            try {
                CommandType commandType = CommandType.valueOf(commandName.toUpperCase());
        		log.debug("Command provider=" + commandType);
                currentCommand = commandType.getCommand();
            } catch (IllegalArgumentException e) {
                log.error("No such command found {}", commandName, e);
                currentCommand = new UnknownCommand();
            }
        } else {
        	currentCommand = new UnknownCommand();
        }
        return currentCommand;
    }
}
