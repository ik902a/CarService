package by.epam.learn.model.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import by.epam.learn.entity.User;
import by.epam.learn.exception.ServiceException;

/**
 * The {@code UserService} interface for operations with the user
 * 
 * @author Ihar Klepcha
 */
public interface UserService {
	
	/**
	 * Signs up the user and adds in the data base with status inactive
	 * 
	 * @param userData {@link Map} of {@link String} and {@link String} user data values
	 * @return boolean true if the user has been added, else false
	 * @throws ServiceException if {@link DaoException} occurs
	 */
	boolean signUp(Map<String, String> userData) throws ServiceException;
	
	/**
	 * Changes the user's status in the data base active 
	 * 
	 * @param login {@link String} login
	 * @return boolean true if the user has been activated, else false
	 * @throws ServiceException if {@link DaoException} occurs
	 */
	boolean activate(String login) throws ServiceException;
	
	/**
	 * Adds the user in the data base
	 * 
	 * @param userData {@link Map} of {@link String} and {@link String} user data values
	 * @return boolean true if the user has been added, else false
	 * @throws ServiceException if {@link DaoException} occurs
	 */
	boolean addUser(Map<String, String> userData) throws ServiceException;
	
	/**
	 * Logs in the user
	 * 
	 * @param login {@link String} login
	 * @param password {@link String} password
	 * @return {@link Optional} of {@link User} received from database if log in is successful, 
	 * else empty optional
	 * @throws ServiceException if {@link DaoException} occurs
	 */
	Optional<User> login(String login, String password) throws ServiceException;
	
	/**
	 * Finds all users in data base
	 * 
	 * @return {@link List} of {@link User} received from database if users are found, else empty list
	 * @throws ServiceException if {@link DaoException} occurs
	 */
	List<User> findAll() throws ServiceException;
	
	/**
	 * Finds user in data base by id
	 * 
	 * @param userIdValue {@link String} value user id
	 * @return {@link User} received from database if user is found, else empty entity
	 * @throws ServiceException if {@link DaoException} occurs
	 */
	User findUserById(String userIdValue) throws ServiceException;
	
	/**
	 * Finds user in data base by login
	 * 
	 * @param loginValue {@link String} login
	 * @return {@link List} of {@link User} received from database if users are found, else empty list
	 * @throws ServiceException if {@link DaoException} occurs
	 */
	List<User> findUserByLogin(String loginValue) throws ServiceException;
	
	/**
	 * Finds mechanics in data base 
	 * 
	 * @return {@link List} of {@link User} received from database if users are found, else empty list
	 * @throws ServiceException if {@link DaoException} occurs
	 */
	List<User> findMechanic() throws ServiceException;

	/**
	 * Updates the client in the data base by himself 
	 * 
	 * @param userData {@link Map} of {@link String} and {@link String} user data values
	 * @param user {@link User} user
	 * @return boolean true if the user has been updated, else false
	 * @throws ServiceException if {@link DaoException} occurs
	 */
	boolean updateClient(Map<String, String> userData, User user) throws ServiceException;
	
	/**
	 * Updates the user in the data base
	 * 
	 * @param userData {@link Map} of {@link String} and {@link String} user data values
	 * @return boolean true if the user has been updated, else false
	 * @throws ServiceException if {@link DaoException} occurs
	 */
	boolean updateUser(Map<String, String> userData, String oldLogin) throws ServiceException;
	
	/**
	 * Deletes the user from the data base
	 * 
	 * @param userId long user id
	 * @return boolean true if the user has been deleted, else false
	 * @throws ServiceException if {@link DaoException} occurs
	 */
	boolean delete(long userId) throws ServiceException;
}
