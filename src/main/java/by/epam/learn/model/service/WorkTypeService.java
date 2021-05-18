package by.epam.learn.model.service;

import java.util.Map;

import by.epam.learn.exception.ServiceException;

public interface WorkTypeService {
	public Map<Long, String> findAllWorkType() throws ServiceException;

}
