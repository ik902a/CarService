package by.epam.learn.model.dao;

import java.util.List;

import by.epam.learn.entity.Price;
import by.epam.learn.exception.DaoException;

/**
 * The {@code PriceDao} interface for working with database table prices
 * 
 * @author Ihar Klepcha
 * @see BaseDao
 */
public interface PriceDao extends BaseDao<Long, Price> {
	
	/**
	 * Looks for a price where this type of work
	 * 
	 * @param workTypeId long id type of work
	 * @return {@link List} of {@link Price} price received from database
	 * @throws DaoException if {@link SQLException} occur
	 */
	List<Price> findPriceByWorkType(long workTypeId) throws DaoException;
	
	/**
	 * Looks for a price where the operation
	 * 
	 * @param operationValue {@link long} value operation
	 * @return {@link List} of {@link Price} price received from database
	 * @throws DaoException if {@link SQLException} occur
	 */
	List<Price> findPriceByOperation(String operationValue) throws DaoException;
}
