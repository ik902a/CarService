package by.epam.learn.model.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

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

/**
 * The {@code UserServiceImpl} class is responsible for operations with the user
 * 
 * @author Ihar Klepcha
 * @see UserService
 */
public class UserServiceImpl implements UserService {
	public static Logger log = LogManager.getLogger();
	private static final String EMPTY = "";
	private static final String PERCENT = "%";

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
				String phone = EMPTY;
				UserRole role = UserRole.CLIENT;
				UserStatus status = UserStatus.INACTIVE;
				User user = new User(login, name, email, phone, role, status);
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
		boolean isActivated;
	    try {
            isActivated = UserDaoImpl.getInstance().activate(login);
        } catch (DaoException e) {
        	log.error("user activate error", e);
            throw new ServiceException("user activate error", e);
        }
        return isActivated;
	}
	
	@Override
	public boolean addUser(Map<String, String> userData) throws ServiceException {
		boolean isAdded = false;
		String login = userData.get(LOGIN_KEY);
		try {
			if (UserDaoImpl.getInstance().containsLogin(login)) {
				userData.put(LOGIN_KEY, login + ALREADY_EXISTS);
			} else if (UserDataValidator.areValidData(userData)) {
				String name = userData.get(NAME_KEY);
				String email = userData.get(EMAIL_KEY);
				String phone = userData.get(PHONE_KEY);
				UserRole role = UserRole.valueOf(userData.get(ROLE_KEY));
				UserStatus status = UserStatus.ACTIVE;
				User user = new User(login, name, email, phone, role, status);
				String password = userData.get(PASSWORD_KEY);
				String hashedPassword = PasswordEncryptor.encrypt(password);
				isAdded = UserDaoImpl.getInstance().addUser(user, hashedPassword);
			}
		} catch (DaoException e) {
			log.error("user add error", e);
			throw new ServiceException("user add error", e);
		}
		return isAdded;
	}

	@Override
	public Optional<User> login(String login, String password) throws ServiceException {
		Optional<User> optionalUser;
	    try {
            optionalUser = UserDaoImpl.getInstance().findUser(login, password);
        } catch (DaoException e) {
        	log.error("user search error", e);
            throw new ServiceException("users search error", e);
        }
        return optionalUser;
	}
	
	@Override
	public List<User> findAll() throws ServiceException {
		List<User> users;
		try {
			users = UserDaoImpl.getInstance().findAll();
		} catch (DaoException e) {
			log.error("user search error", e);
			throw new ServiceException("users search error", e);
		}
		return users;
	}
	
	@Override
	public User findUserById(String userIdValue) throws ServiceException {
		User user;
		long userId = Long.parseLong(userIdValue);
	    try {
            user = UserDaoImpl.getInstance().findEntityById(userId);
        } catch (DaoException e) {
        	log.error("user search error", e);
            throw new ServiceException("user search error", e);
        }
        return user;
	}
	
	@Override
	public List<User> findUserByLogin(String loginValue) throws ServiceException {
		loginValue = PERCENT + loginValue + PERCENT;
		List<User> users;
		try {
			users = UserDaoImpl.getInstance().findUserByLogin(loginValue);
		} catch (DaoException e) {
			log.error("user search error", e);
			throw new ServiceException("users search error", e);
		}
		return users;
	}
	
	@Override
	public List<User> findMechanic() throws ServiceException {
		List<User> mechanics;
		try {
			mechanics = UserDaoImpl.getInstance().findMechanic();
		} catch (DaoException e) {
			log.error("user search error", e);
			throw new ServiceException("users search error", e);
		}
		return mechanics;
	}
	
	@Override
	public boolean updateUser(Map<String, String> userData, String oldLogin) throws ServiceException {
		boolean isUpdate = false;
		String updateLogin = userData.get(LOGIN_KEY);
		log.debug("Service update - login={}", updateLogin);
		try {
			if (!oldLogin.equals(updateLogin) && UserDaoImpl.getInstance().containsLogin(updateLogin)) {
				userData.put(LOGIN_KEY, updateLogin + ALREADY_EXISTS);
			} else if (UserDataValidator.areValidData(userData)) {
				long userId = Long.parseLong(userData.get(ID_KEY));
				String updateName = userData.get(NAME_KEY);
				String updateEmail = userData.get(EMAIL_KEY);
				String updatePhone = userData.get(PHONE_KEY);
				UserRole role = UserRole.valueOf(userData.get(ROLE_KEY));
				UserStatus status = UserStatus.valueOf(userData.get(STATUS_KEY));
				User updateUser = new User(userId, updateLogin, updateName, updateEmail, updatePhone, role, status);
				isUpdate = UserDaoImpl.getInstance().update(updateUser);
			}
		} catch (DaoException e) {
			log.error("users update error", e);
			throw new ServiceException("users update error", e);
		}
		return isUpdate;
	}
	
	@Override
	public boolean updateClient(Map<String, String> userData, User user) throws ServiceException {
		boolean isUpdate = false;
		String login = user.getLogin();
		String updateLogin = userData.get(LOGIN_KEY);
		log.debug("Service update - login={}", login);
		try {
			if (!login.equals(updateLogin) && UserDaoImpl.getInstance().containsLogin(updateLogin)) {
				userData.put(LOGIN_KEY, login + ALREADY_EXISTS);
			} else if (UserDataValidator.areValidData(userData)) {
				long userId = user.getUserId();
				String updateName = userData.get(NAME_KEY);
				String updateEmail = userData.get(EMAIL_KEY);
				String updatePhone = userData.get(PHONE_KEY);
				UserRole role = UserRole.CLIENT;
				UserStatus status = UserStatus.ACTIVE;
				User updateUser = new User(userId, updateLogin, updateName, updateEmail, updatePhone, role, status);
				isUpdate = UserDaoImpl.getInstance().update(updateUser);
			}
		} catch (DaoException e) {
			log.error("client update error", e);
			throw new ServiceException("client update error", e);
		}
		return isUpdate;
	}

	@Override
	public boolean delete(long userId) throws ServiceException {
		boolean isDeleted;
	    try {
            isDeleted = UserDaoImpl.getInstance().delete(userId);
        } catch (DaoException e) {
        	log.error("user delete error", e);
            throw new ServiceException("user delete error", e);
        }
        return isDeleted;
	}
}
