package by.epam.learn.model.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.learn.entity.Entity;
import by.epam.learn.exception.DaoException;

/**
 * The {@code BaseDao} is the root interface in the DAO hierarchy
 * 
 * @author Ihar Klepcha
 *
 * @param <K> wrapper
 * @param <T> entity
 */
public interface BaseDao<K, T extends Entity> {
	public static Logger log = LogManager.getLogger();

	/**
	 * Looks for all entity
	 * 
	 * @param userId {@link long} user id
	 * @return {@link List} of {@link T} entity received from database
	 * @throws DaoException if {@link SQLException} occur
	 */
	List<T> findAll() throws DaoException;

	/**
	 * Looks for an entity where entity has id
	 * 
	 * @param id {@link K} entity id
	 * @return {@link T} entity received from database
	 * @throws DaoException if {@link SQLException} occur
	 */
	T findEntityById(K id) throws DaoException;

	/**
	 * Deletes a record in the corresponding database table
	 * 
	 * @param id {@link K} entity id
	 * @return boolean true if the record has been deleted, else false
	 * @throws DaoException if {@link SQLException} occur
	 */
	boolean delete(K id) throws DaoException;

	/**
	 * Creates a record in the corresponding database table
	 * 
	 * @param t {@link T} entity
	 * @return boolean true if the record has been created, else false
	 * @throws DaoException if {@link SQLException} occur
	 */
	boolean create(T t) throws DaoException;

	/**
	 * Updates a record in the corresponding database table
	 * 
	 * @param t {@link T} entity
	 * @return boolean true if the record has been updated, else false
	 * @throws DaoException if {@link SQLException} occur
	 */
	boolean update(T t) throws DaoException;

	/**
	 * Closes a statement
	 * 
	 * @param statement {@link Statement} statement
	 */
	default void close(Statement statement) {
		try {
			if (statement != null) {
				statement.close();
			}
		} catch (SQLException e) {
			log.error("ERROR while closing statement", e);
		}
	}

	/**
	 * Closes a connection
	 * 
	 * @param connection {@link Connection} connection
	 */
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
