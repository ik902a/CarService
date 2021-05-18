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
import by.epam.learn.exception.DaoException;
import by.epam.learn.model.dao.WorkTypeDao;
import by.epam.learn.model.pool.ConnectionPool;

import static by.epam.learn.model.dao.ColumnName.*;

public class WorkTypeDaoImpl implements WorkTypeDao {
	public static Logger log = LogManager.getLogger();
	private static final WorkTypeDao instance = new WorkTypeDaoImpl();
	
	private static final String SQL_FIND_ALL_SERVICE = "SELECT idworktype, work_type FROM work_types";
	
	private WorkTypeDaoImpl() {
	}

	public static WorkTypeDao getInstance() {
		return instance;
	}

	@Override
	public List<WorkType> findAll() throws DaoException {
		   List<WorkType> works = new ArrayList<>();
	        try (Connection connection = ConnectionPool.getInstance().getConnection();
	             PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_SERVICE)) {
	            
	            ResultSet workTypeData = statement.executeQuery();
	            
	            while (workTypeData.next()) {
	            	long workTypeId = workTypeData.getInt(WORK_TYPES_IDWORKTYPE);
	            	String workType = workTypeData.getNString(WORK_TYPSE_WORK_TYPE);
	            	
	            	WorkType work = new WorkType(workTypeId, workType);
	            	
	            	works.add(work);
	                 
	            }
	        } catch (SQLException e) {
	            throw new DaoException(e);
	        }
	        return works;
	}

	@Override
	public WorkType findEntityById(Integer id) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(WorkType t) throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Integer id) throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean create(WorkType t) throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(WorkType t) throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}

}
