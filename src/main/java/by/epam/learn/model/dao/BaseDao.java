package by.epam.learn.model.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.learn.entity.Entity;
import by.epam.learn.exception.DaoException;

public interface BaseDao <K, T extends Entity> {
	public static Logger log = LogManager.getLogger();
	
	List<T> findAll() throws DaoException;
	T findEntityById(K id) throws DaoException;
	boolean delete(T t) throws DaoException;
	boolean delete(K id) throws DaoException;
	boolean create(T t) throws DaoException;
	boolean update(T t) throws DaoException;
	
	default void close(Statement statement) {
		try {
			if (statement != null) {
				statement.close();
			}
		} catch (SQLException e) {
			log.error("ERROR while closing statement", e);
		}
	}
	default void close(Connection connection) {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			log.error("ERROR while closing connection", e);
		}
	}
}
