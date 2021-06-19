package by.epam.learn.model.dao;

import java.util.List;
import java.util.Optional;

import by.epam.learn.exception.DaoException;
import by.epam.learn.entity.User;

/**
 * The {@code UserDao} interface for working with database table users
 * 
 * @author Ihar Klepcha
 * @see BaseDao
 */
public interface UserDao extends BaseDao<Long, User> {
	
	/**
	 * Creates a new record in the corresponding database table
	 * 
	 * @param user {@link User} user
	 * @param hashedPassword {@link String} password
	 * @return boolean true if the record has been added, else false
	 * @throws DaoException if {@link SQLException} occur
	 */
	boolean addUser(User user, String hashedPassword) throws DaoException;
	
	/**
	 * Checks record login in the corresponding database table
	 * 
	 * @param login {@link String} login
	 * @return boolean true if the record has had login, else false
	 * @throws DaoException if {@link SQLException} occur
	 */
	boolean containsLogin(String login) throws DaoException;
	
	/**
	 * Activates record in the corresponding database table
	 * 
	 * @param login {@link String} login
	 * @return boolean true if the record has been activated, else false
	 * @throws DaoException if {@link SQLException} occur
	 */
	boolean activate(String login) throws DaoException;
	
	/**
	 * Looks for a user where the user has login and password
	 * 
	 * @param login {@link String} login
	 * @param password {@link String} password
	 * @return {@link Optional} of {@link User} user received from database
	 * @throws DaoException if {@link SQLException} occur
	 */
	Optional<User> findUser(String login, String password) throws DaoException;
	
	/**
	 * Looks for a user where the user has login
	 * 
	 * @param loginValue {@link String} login
	 * @return {@link List} of {@link User} user received from database
	 * @throws DaoException if {@link SQLException} occur
	 */
	List<User> findUserByLogin(String loginValue) throws DaoException;
	
	/**
	 * Looks for a user who is mechanic
	 * 
	 * @return {@link List} of {@link User} user received from database
	 * @throws DaoException if {@link SQLException} occur
	 */
	List<User> findMechanic() throws DaoException;
}
