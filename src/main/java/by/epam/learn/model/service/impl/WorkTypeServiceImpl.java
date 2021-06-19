package by.epam.learn.model.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.learn.entity.WorkType;
import by.epam.learn.exception.DaoException;
import by.epam.learn.exception.ServiceException;
import by.epam.learn.model.dao.impl.WorkTypeDaoImpl;
import by.epam.learn.model.service.WorkTypeService;

/**
 * The {@code WorkTypeServiceImpl} class is responsible for operations with the type of work
 * 
 * @author Ihar Klepcha
 * @see WorkTypeService
 */
public class WorkTypeServiceImpl implements WorkTypeService {
	public static Logger log = LogManager.getLogger();

	@Override
	public List<WorkType> findAllWorkType() throws ServiceException {
		List<WorkType> works;
		try {
			works = WorkTypeDaoImpl.getInstance().findAll();
		} catch (DaoException e) {
			log.error("type of work search error", e);
			throw new ServiceException("type of work search error", e);
		}
		return works;
	}
}
