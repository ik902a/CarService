package by.epam.learn.model.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.learn.entity.WorkType;
import by.epam.learn.exception.DaoException;
import by.epam.learn.exception.ServiceException;
import by.epam.learn.model.dao.impl.WorkTypeDaoImpl;
import by.epam.learn.model.service.WorkTypeService;

public class WorkTypeServiceImpl implements WorkTypeService {
	public static Logger log = LogManager.getLogger();
	private static final String PROPERTY = "clientprofile.";

	@Override
	public Map<Long, String> findAllWorkType() throws ServiceException {
		List<WorkType> works = new ArrayList<>();
		try {
			
			works = WorkTypeDaoImpl.getInstance().findAll();
			
		} catch (DaoException e) {
			throw new ServiceException("type of work search error", e);
		}
	
		Map<Long, String> workData = works.stream()
							.collect(Collectors.toMap(work -> (Long) work.getWorkTypeId(), 
												work -> (String) PROPERTY + work.getWorkType()));

		return workData;
	}
}
