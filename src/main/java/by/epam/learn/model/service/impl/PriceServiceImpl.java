package by.epam.learn.model.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.learn.entity.Order;
import by.epam.learn.entity.Price;
import by.epam.learn.entity.WorkType;
import by.epam.learn.exception.DaoException;
import by.epam.learn.exception.ServiceException;
import by.epam.learn.model.dao.impl.PriceDaoImpl;
import by.epam.learn.model.service.PriceService;
import by.epam.learn.validator.PriceDataValidator;

import static by.epam.learn.controller.command.DataKeyword.*;

/**
 * The {@code PriceServiceImpl} class is responsible for operations with the price
 * 
 * @author Ihar Klepcha
 * @see PriceService
 */
public class PriceServiceImpl implements PriceService {
	public static Logger log = LogManager.getLogger();
	private static final String PERCENT = "%";
	
	@Override
	public boolean addPrice(Map<String, String> priceData) throws ServiceException {
		boolean isAdded = false;
		try {
			if (PriceDataValidator.areValidData(priceData)) {
				String operation = priceData.get(OPERATION_KEY);
				double price = Double.parseDouble(priceData.get(PRICE_KEY));
				long workId = Long.parseLong(priceData.get(WORK_TYPE_KEY));
				WorkType workType = new WorkType();
				workType.setWorkTypeId(workId);
				Price priceList = new Price(operation, price, workType);
				isAdded = PriceDaoImpl.getInstance().create(priceList);
			} 
		} catch (DaoException e) {
			log.error("price adding error", e);
			throw new ServiceException("prices adding error", e);
		}
		return isAdded;
	}
	
	@Override
	public List<Price> findAll() throws ServiceException {
		List<Price> prices;
		try {
			prices = PriceDaoImpl.getInstance().findAll();
		} catch (DaoException e) {
			prices = new ArrayList<>();
			log.error("price search error", e);
			throw new ServiceException("prices search error", e);
		}
		return prices;
	}
	
	@Override
	public Price findPriceById(String priceIdValue) throws ServiceException {
		Price price;
		long priceId = Long.parseLong(priceIdValue);
	    try {
            price = PriceDaoImpl.getInstance().findEntityById(priceId);
        } catch (DaoException e) {
        	price = new Price();
        	log.error("price search error", e);
            throw new ServiceException("price search error", e);
        }
        return price;
	}

	@Override
	public List<Price> findPriceByWorkType(Order order) throws ServiceException {
		List<Price> prices;
		long workTypeId = order.getWorkType().getWorkTypeId();
		try {
			prices = PriceDaoImpl.getInstance().findPriceByWorkType(workTypeId);
		} catch (DaoException e) {
			log.error("price search error", e);
			throw new ServiceException("prices search error", e);
		}
		return prices;
	}
	
	@Override
	public List<Price> findPriceByOperation(String operationValue) throws ServiceException {
		List<Price> prices;
		operationValue = PERCENT + operationValue + PERCENT;
		try {
			prices = PriceDaoImpl.getInstance().findPriceByOperation(operationValue);
		} catch (DaoException e) {
			log.error("price search error", e);
			throw new ServiceException("prices search error", e);
		}
		return prices;
	}
	
	@Override
	public boolean updatePrice(Map<String, String> priceData) throws ServiceException {
		boolean isUpdate = false;
		try {
			 if (PriceDataValidator.areValidData(priceData)) {
				long priceId = Long.parseLong(priceData.get(ID_KEY));
				double price = Double.parseDouble(priceData.get(PRICE_KEY));
				Price updatePriceList = new Price();
				updatePriceList.setPriceId(priceId);
				updatePriceList.setPrice(price);
				isUpdate = PriceDaoImpl.getInstance().update(updatePriceList);
			}
		} catch (DaoException e) {
			log.error("price update error", e);
			throw new ServiceException("price update error", e);
		}
		return isUpdate;
	}

	@Override
	public boolean delete(String priceIdValue) throws ServiceException {
		boolean isDeleted = false;
		long priceId = Long.parseLong(priceIdValue);
	    try {
            isDeleted = PriceDaoImpl.getInstance().delete(priceId);
        } catch (DaoException e) {
        	log.error("price delete error", e);
            throw new ServiceException("price delete error", e);
        }
        return isDeleted;
	}
}
