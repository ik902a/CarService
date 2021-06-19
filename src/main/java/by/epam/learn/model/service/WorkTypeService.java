package by.epam.learn.model.service;

import java.util.List;

import by.epam.learn.entity.WorkType;
import by.epam.learn.exception.ServiceException;

/**
 * The {@code WorkTypeService} interface for operations with the type of work
 * 
 * @author Ihar Klepcha
 */
public interface WorkTypeService {
	
	/**
	 * Finds all type of work in data base
	 * 
	 * @return {@link List} of {@link WorkType} received from database if type of work are found, 
	 * else empty list
	 * @throws ServiceException if {@link DaoException} occurs
	 */
	List<WorkType> findAllWorkType() throws ServiceException;
}
