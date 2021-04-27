package by.epam.learn.model.service.impl;

import java.util.Map;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.learn.exception.DaoException;
import by.epam.learn.exception.ServiceException;
import by.epam.learn.model.dao.impl.UserDaoImpl;
import by.epam.learn.entity.User;
import by.epam.learn.entity.UserRole;
import by.epam.learn.entity.UserStatus;
import by.epam.learn.model.service.UserService;
import by.epam.learn.util.PasswordEncryptor;
import by.epam.learn.validator.UserDataValidator;

import static by.epam.learn.controller.command.DataKeyword.*;

public class UserServiceImpl implements UserService {
	public static Logger log = LogManager.getLogger();

	@Override
	public boolean signUp(Map<String, String> userData) throws ServiceException {
		boolean isSignUp = false;
		String login = userData.get(LOGIN_KEY);
		try {
			if (UserDaoImpl.getInstance().containsLogin(login)) {
				userData.put(LOGIN_KEY, login + ALREADY_EXISTS);
			} else if (UserDataValidator.areValidData(userData)) {
				String name = userData.get(NAME_KEY);
				String email = userData.get(EMAIL_KEY);
				String role = UserRole.CLIENT.toString().toLowerCase();
				String status = UserStatus.UNACTIVED.toString().toLowerCase();
				User user = new User(login, name, email, role, status);
				String password = userData.get(PASSWORD_KEY);
				String hashedPassword = PasswordEncryptor.encrypt(password);
				isSignUp = UserDaoImpl.getInstance().addUser(user, hashedPassword);
			} else {
				isSignUp = false;
			}
		} catch (DaoException e) {
			log.error(e);
			throw new ServiceException(e);
		}
		
//		TODO
//        if (successfullySignedUp) {
//            String emailValue = userData.get(EMAIL_KEY);
//            String topic = "Welcome to GO!";
//            String text = "Thank you for registering on our rental service. Wish you a great road!";
//            sendMail(emailValue, topic, text);
//        }



		return isSignUp;
	}

	
	@Override
	public Optional<User> login(String login, String password) throws ServiceException {//TODO add Validator
		Optional<User> optionalUser;
	    try {
            optionalUser = UserDaoImpl.getInstance().findUser(login, password);
        } catch (DaoException e) {
            throw new ServiceException(e);// TODO add to message e-mail
        }
        return optionalUser;
	}
	

}
