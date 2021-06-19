package by.epam.learn.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.learn.entity.WorkType;
import by.epam.learn.exception.ConnectionPoolException;
import by.epam.learn.exception.DaoException;
import by.epam.learn.model.dao.WorkTypeDao;
import by.epam.learn.model.pool.ConnectionPool;

/**
 * The {@code WorkTypeDaoImpl} class works with database table work-types
 * 
 * @author Ihar Klepcha
 * @see WorkTypeDao
 */
public class WorkTypeDaoImpl implements WorkTypeDao {
	public static Logger log = LogManager.getLogger();
	private static final WorkTypeDao instance = new WorkTypeDaoImpl();
	private static final String SQL_FIND_ALL_WORK = "SELECT idworktype, work_type FROM work_types";
	
	private WorkTypeDaoImpl() {
	}

	public static WorkTypeDao getInstance() {
		return instance;
	}

	@Override
	public List<WorkType> findAll() throws DaoException {
		List<WorkType> works = new ArrayList<>();
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_WORK)) {
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				WorkType work = DaoEntityBuilder.buildWorkType(resultSet);
				works.add(work);
			}
		} catch (ConnectionPoolException | SQLException e) {
			log.error("exception while finding type of work", e);
			throw new DaoException("database error", e);
		}
		return works;
	}

	@Override
	public WorkType findEntityById(Long id) throws DaoException {
		throw new UnsupportedOperationException("operation not supported for class " + this.getClass().getName());
	}

	@Override
	public boolean delete(Long id) throws DaoException {
		throw new UnsupportedOperationException("operation not supported for class " + this.getClass().getName());
	}

	@Override
	public boolean create(WorkType t) throws DaoException {
		throw new UnsupportedOperationException("operation not supported for class " + this.getClass().getName());
	}

	@Override
	public boolean update(WorkType t) throws DaoException {
		throw new UnsupportedOperationException("operation not supported for class " + this.getClass().getName());
	}
}
