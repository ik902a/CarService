package by.epam.learn.model.dao;

import java.util.Optional;

import by.epam.learn.exception.DaoException;
import by.epam.learn.entity.User;

public interface UserDao extends BaseDao<Integer, User> {
	
	boolean containsLogin(String email) throws DaoException;
	
	boolean addUser(User user, String hashedPassword) throws DaoException;
	
	Optional<User> findUser(String login, String password) throws DaoException;

}
