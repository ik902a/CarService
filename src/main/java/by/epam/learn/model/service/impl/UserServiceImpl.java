package by.epam.learn.model.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.mail.MessagingException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.learn.exception.DaoException;
import by.epam.learn.exception.ServiceException;
import by.epam.learn.model.dao.impl.UserDaoImpl;
import by.epam.learn.entity.EmailMessage;
import by.epam.learn.entity.User;
import by.epam.learn.entity.UserRole;
import by.epam.learn.entity.UserStatus;
import by.epam.learn.model.service.UserService;
import by.epam.learn.util.EmailMessageFactory;
import by.epam.learn.util.MailSender;
import by.epam.learn.util.PasswordEncryptor;
import by.epam.learn.validator.UserDataValidator;

import static by.epam.learn.controller.command.DataKeyword.*;

public class UserServiceImpl implements UserService {
	public static Logger log = LogManager.getLogger();

	@Override
	public boolean signUp(Map<String, String> userData) throws ServiceException {
		boolean isSignUp = false;
		String login = userData.get(LOGIN_KEY);
		log.debug("Service - login=" + login);
		try {
			if (UserDaoImpl.getInstance().containsLogin(login)) {
				userData.put(LOGIN_KEY, login + ALREADY_EXISTS);
			} else if (UserDataValidator.areValidData(userData)) {
				log.debug("Service login-" + login);
				String name = userData.get(NAME_KEY);
				String email = userData.get(EMAIL_KEY);
				UserRole role = UserRole.CLIENT;
				UserStatus status = UserStatus.INACTIVE;
				User user = new User(login, name, email, role, status);
				String password = userData.get(PASSWORD_KEY);
				String hashedPassword = PasswordEncryptor.encrypt(password);
				isSignUp = UserDaoImpl.getInstance().addUser(user, hashedPassword);
		        if (isSignUp) {
		            EmailMessage emailMessage = EmailMessageFactory.createSignUpMessage(user);
					MailSender.send(emailMessage);
		        }
			}
		} 
		catch (MessagingException | DaoException e) {
			log.error("user creation error", e);
			throw new ServiceException("user creation error", e);
		}
		return isSignUp;
	}
	
	@Override
	public boolean activate(String login) throws ServiceException {
		boolean isActivated = false;
	    try {
            isActivated = UserDaoImpl.getInstance().activate(login);
        } catch (DaoException e) {
            throw new ServiceException(e);// TODO add to message e-mail
        }
        return isActivated;
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


	@Override
	public boolean updateClient(Map<String, String> userData, User user) throws ServiceException {
		boolean isUpdate = false;
		String login = user.getLogin();
		String updateLogin = userData.get(LOGIN_KEY);
		log.debug("Service update - login={}", login);
		try {
			if (!login.equals(updateLogin) && UserDaoImpl.getInstance().containsLogin(login)) {
				userData.put(LOGIN_KEY, login + ALREADY_EXISTS);
			} else if (UserDataValidator.areValidUpdateData(userData)) {
				log.debug("Service -" + updateLogin);
				long userId = user.getUserId();
				String updateName = userData.get(NAME_KEY);
				String updateEmail = userData.get(EMAIL_KEY);
				String updatePhone = userData.get(PHONE_KEY);
				UserRole role = UserRole.CLIENT;
				UserStatus status = UserStatus.ACTIVE;
				User updateUser = new User(userId, updateLogin, updateName, updateEmail, updatePhone, role, status);

				isUpdate = UserDaoImpl.getInstance().updateClient(updateUser);
			} else {
				isUpdate = false;
			}
		} catch (DaoException e) {
			log.error(e);
			throw new ServiceException(e);
		}
		return isUpdate;
	}


	@Override
	public List<User> findMechanic() throws ServiceException {
	
		Optional<User> mechanics = Optional.empty();
		try {
			mechanics = UserDaoImpl.getInstance().findMechanic();
		} catch (DaoException e) {
			throw new ServiceException("cars search error", e);
		}
		
		List<User> mechanicList = mechanics.stream()
				.collect(Collectors.toList());// Что делать если не страшно если будет пустой лист?
		
		return mechanicList;
	}



	
}
