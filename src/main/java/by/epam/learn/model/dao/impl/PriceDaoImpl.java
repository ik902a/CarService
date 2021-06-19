package by.epam.learn.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.learn.entity.Price;
import by.epam.learn.exception.ConnectionPoolException;
import by.epam.learn.exception.DaoException;
import by.epam.learn.model.dao.PriceDao;
import by.epam.learn.model.pool.ConnectionPool;

/**
 * The {@code PriceDaoImpl} class works with database table prices
 * 
 * @author Ihar Klepcha
 * @see PriceDao
 */
public class PriceDaoImpl implements PriceDao {
	public static Logger log = LogManager.getLogger();
	private static final PriceDao instance = new PriceDaoImpl();
	private static final String SQL_ADD_PRICE = "INSERT INTO prices(operation, price, work_type_id) "
			+ "VALUES (?, ?, ?)";
	private static final String SQL_FIND_PRICE = "SELECT idprice, operation, price,"
			+ "work_types.idworktype, work_types.work_type "
			+ "FROM prices "
			+ "JOIN work_types ON prices.work_type_id=work_types.idworktype";
	private static final String SQL_FIND_PRICE_BY_ID = "SELECT idprice, operation, price, "
			+ "work_types.idworktype, work_types.work_type "
			+ "FROM prices "
			+ "JOIN work_types ON prices.work_type_id=work_types.idworktype "
			+ "WHERE prices.idprice=?";
	private static final String SQL_FIND_PRICE_BY_WORK_TYPE = "SELECT idprice, operation, price, "
			+ "work_types.idworktype, work_types.work_type "
			+ "FROM prices "
			+ "JOIN work_types ON prices.work_type_id=work_types.idworktype "
			+ "WHERE prices.work_type_id=?";
	private static final String SQL_FIND_PRICE_BY_OPERATION = "SELECT idprice, operation, price, "
			+ "work_types.idworktype, work_types.work_type "
			+ "FROM prices "
			+ "JOIN work_types ON prices.work_type_id=work_types.idworktype "		
			+ "WHERE operation LIKE ?";
	private static final String SQL_UPDATE = "UPDATE prices SET price=? WHERE idprice=?";
	private static final String SQL_DELETE = "DELETE FROM prices WHERE idprice=?";
	
	private PriceDaoImpl() {
	}

	public static PriceDao getInstance() {
		return instance;
	}
	
	@Override
	public boolean create(Price priceList) throws DaoException {
		boolean isAdded;
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_ADD_PRICE)) {
			String operation = priceList.getOperation();
			double price = priceList.getPrice();
			long workTypeId = priceList.getWorkType().getWorkTypeId();
			statement.setString(1, operation);
			statement.setDouble(2, price);
			statement.setLong(3, workTypeId);
			int rowCount = statement.executeUpdate();
			isAdded = (rowCount != 0);
		} catch (ConnectionPoolException | SQLException e) {
			log.error("exception while adding new price", e);
			throw new DaoException("database error", e);
		}
		return isAdded;
	}
	
	@Override
	public List<Price> findAll() throws DaoException {
		List<Price> prices = new ArrayList<>();
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_FIND_PRICE)) {
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Price price = DaoEntityBuilder.buildPrice(resultSet);
				prices.add(price);
			}
		} catch (ConnectionPoolException | SQLException e) {
			log.error("exception while finding a price", e);
			throw new DaoException("database error", e);
		}
		return prices;
	}
	
	@Override
	public Price findEntityById(Long priceId) throws DaoException {
		Price price;
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_FIND_PRICE_BY_ID)) {
			statement.setLong(1, priceId);
			ResultSet resultSet = statement.executeQuery();
			price = (resultSet.next()) ? DaoEntityBuilder.buildPrice(resultSet) : new Price();
		} catch (ConnectionPoolException | SQLException e) {
			log.error("exception while finding price", e);
			throw new DaoException("database error", e);
		}
		return price;
	}
	
	@Override
	public List<Price> findPriceByWorkType(long workTypeId) throws DaoException {
		List<Price> prices = new ArrayList<>();
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_FIND_PRICE_BY_WORK_TYPE)) {
			statement.setLong(1, workTypeId);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Price price = DaoEntityBuilder.buildPrice(resultSet);
				prices.add(price);
			}
		} catch (ConnectionPoolException | SQLException e) {
			log.error("exception while finding price", e);
			throw new DaoException("database error", e);
		}
		return prices;
	}
	
	@Override
	public List<Price> findPriceByOperation(String operationValue) throws DaoException {
		List<Price> prices = new ArrayList<>();
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_FIND_PRICE_BY_OPERATION)) {
			statement.setString(1, operationValue);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Price price = DaoEntityBuilder.buildPrice(resultSet);
				prices.add(price);
			}
		} catch (ConnectionPoolException | SQLException e) {
			log.error("exception while finding price", e);
			throw new DaoException("database error", e);
		}
		return prices;
	}
	
	@Override
	public boolean update(Price priceList) throws DaoException {
		boolean isUpdate;
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_UPDATE)) {
			long priceId = priceList.getPriceId();
			double price = priceList.getPrice();
			statement.setDouble(1, price);
			statement.setLong(2, priceId);
			int rowCount = statement.executeUpdate();
			isUpdate = (rowCount != 0);
		} catch (ConnectionPoolException | SQLException e) {
			log.error("exception while updating price", e);
			throw new DaoException("database error", e);
		}
		return isUpdate;
	}
	
	@Override
	public boolean delete(Long priceId) throws DaoException {
		boolean isDeleted;
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_DELETE)) {
			statement.setLong(1, priceId);
			int rowCount = statement.executeUpdate();
			isDeleted = (rowCount != 0);
		} catch (ConnectionPoolException | SQLException e) {
			log.error("exception while delete price", e);
			throw new DaoException("database error", e);
		}
		return isDeleted;
	}
}
