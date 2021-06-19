package by.epam.learn.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.learn.entity.User;
import by.epam.learn.entity.UserRole;
import by.epam.learn.entity.UserStatus;
import by.epam.learn.exception.ConnectionPoolException;
import by.epam.learn.exception.DaoException;
import by.epam.learn.model.dao.UserDao;
import by.epam.learn.model.pool.ConnectionPool;
import by.epam.learn.util.PasswordEncryptor;

import static by.epam.learn.model.dao.ColumnName.*;

/**
 * The {@code UserDaoImpl} class works with database table users
 * 
 * @author Ihar Klepcha
 * @see UserDao
 */
public class UserDaoImpl implements UserDao {
	public static Logger log = LogManager.getLogger();
	private static final UserDao instance = new UserDaoImpl();
	private static final String SQL_ADD_USER = "INSERT INTO users(login, name, email, password, phone, "
			+ "role, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
	private static final String SQL_CONTAINS_LOGIN = "SELECT 1 FROM users WHERE login=?";
	private static final String SQL_ACTIVATE_CLIENT = "UPDATE users SET status=? WHERE login=?";
	private static final String SQL_FIND_USER = "SELECT iduser, login, name, email, password, phone, role, "
			+ "status FROM users WHERE login=?";
	private static final String SQL_FIND_ALL_USER = "SELECT iduser, login, name, email, password, phone,"
			+ "role, status FROM users";
	private static final String SQL_FIND_USER_BY_ID = "SELECT iduser, login, name, email, password, phone, "
			+ "role, status FROM users WHERE iduser=?";
	private static final String SQL_FIND_USER_BY_LOGIN = "SELECT iduser, login, name, email, password, "
			+ "phone, role, status FROM users WHERE login LIKE ?";
	private static final String SQL_FIND_MECHANIC = "SELECT iduser, login, name, email, password, phone,"
			+ "role, status FROM users WHERE role='MECHANIC'";
	private static final String SQL_UPDATE = "UPDATE users SET login=?, name=?, email=?, phone=? "
			+ "WHERE iduser=?";
	private static final String SQL_DELETE_USER = "UPDATE users SET status=? WHERE iduser=?";

	private UserDaoImpl() {
	}

	public static UserDao getInstance() {
		return instance;
	}
	
	@Override
	public boolean addUser(User user, String hashedPassword) throws DaoException {
		boolean isAdded;
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_ADD_USER)) {
			String login = user.getLogin();
			log.debug("Dao - login=" + login);
			String name = user.getName();
			String email = user.getEmail();
			String phone = user.getPhone();
			UserRole role = user.getRole();
			UserStatus status = user.getStatus();
			statement.setString(1, login);
			statement.setString(2, name);
			statement.setString(3, email);
			statement.setString(4, hashedPassword);
			statement.setString(5, phone);
			statement.setString(6, role.toString());
			statement.setString(7, status.toString());
			int rowCount = statement.executeUpdate();
			isAdded = (rowCount != 0);
		} catch (ConnectionPoolException | SQLException e) {
			log.error("exception while adding new user", e);
			throw new DaoException("database error", e);
		}
		return isAdded;
	}

	@Override
	public boolean containsLogin(String login) throws DaoException {
		boolean isContains;
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_CONTAINS_LOGIN)) {
			log.debug("DAO - constaintlogin=" + login);
			statement.setString(1, login);
			ResultSet resultSet = statement.executeQuery();
			isContains = resultSet.next();
		} catch (ConnectionPoolException | SQLException e) {
			log.error("exception while cheking login", e);
			throw new DaoException("database error", e);
		}
		return isContains;
	}
	
	@Override
	public boolean activate(String login) throws DaoException {
		boolean isActivated;
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_ACTIVATE_CLIENT)) {
			UserStatus status = UserStatus.ACTIVE;
			statement.setString(1, status.toString());
			statement.setString(2, login);
			int rowCount = statement.executeUpdate();
			isActivated = (rowCount != 0);
		} catch (ConnectionPoolException | SQLException e) {
			log.error("Exception while activate user", e);
			throw new DaoException("database error", e);
		}
		return isActivated;
	}
	
	@Override
	public User findEntityById(Long id) throws DaoException {
		User user;
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER_BY_ID)) {
			statement.setLong(1, id);
			ResultSet resultSet = statement.executeQuery();
			user = (resultSet.next()) ? DaoEntityBuilder.buildUser(resultSet) : new User();
		} catch (ConnectionPoolException | SQLException e) {
			log.error("exception while finding user", e);
			throw new DaoException("database error", e);
		}
		return user;
	}
	
	@Override
	public List<User> findAll() throws DaoException {
		List<User> users = new ArrayList<>();
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_USER)) {
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				User user = DaoEntityBuilder.buildUser(resultSet);
				users.add(user);
			}
		} catch (ConnectionPoolException | SQLException e) {
			log.error("exception while finding user", e);
			throw new DaoException("database error", e);
		}
		return users;
	}

	@Override
	public Optional<User> findUser(String login, String password) throws DaoException {
		Optional<User> optionalUser;
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER)) {
			statement.setString(1, login);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				String hashedPassword = resultSet.getNString(USERS_PASSWORD);
				if (PasswordEncryptor.checkPassword(password, hashedPassword)) {
					User user = DaoEntityBuilder.buildUser(resultSet);
					optionalUser = Optional.of(user);
				} else {
					log.error("Incorrect password inputted for login {}", login);
					optionalUser = Optional.empty();
				}
			} else {
				optionalUser = Optional.empty();
			}
		} catch (ConnectionPoolException | SQLException e) {
			log.error("exception while finding user", e);
			throw new DaoException("database error", e);
		}
		return optionalUser;
	}

	@Override
	public List<User> findUserByLogin(String loginValue) throws DaoException {
		List<User> users =  new ArrayList<>();
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER_BY_LOGIN)) {
			statement.setString(1, loginValue);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				User user = DaoEntityBuilder.buildUser(resultSet);
				users.add(user);
			}
		} catch (ConnectionPoolException | SQLException e) {
			log.error("exception while finding user", e);
			throw new DaoException("database error", e);
		}
		return users;
	}
	
	@Override
	public List<User> findMechanic() throws DaoException {
		List<User> mechanics = new ArrayList<>();
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_FIND_MECHANIC)) {
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				User mechanic = DaoEntityBuilder.buildUser(resultSet);
				mechanics.add(mechanic);
			}
		} catch (ConnectionPoolException | SQLException e) {
			log.error("exception while findding mechanic", e);
			throw new DaoException("database error", e);
		}
		return mechanics;
	}
	
	@Override
	public boolean update(User user) throws DaoException {
		boolean isUpdate;
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_UPDATE)) {
			long userId = user.getUserId();
			String login = user.getLogin();
			String name = user.getName();
			String email = user.getEmail();
			String phone = user.getPhone();
			statement.setString(1, login);
			statement.setString(2, name);
			statement.setString(3, email);
			statement.setString(4, phone);
			statement.setLong(5, userId);
			int rowCount = statement.executeUpdate();
			isUpdate = (rowCount != 0);
		} catch (ConnectionPoolException | SQLException e) {
			log.error("exception while updating user", e);
			throw new DaoException("database error", e);
		}
		return isUpdate;
	}
	
	@Override
	public boolean delete(Long id) throws DaoException {
		boolean isDeleted;
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_DELETE_USER)) {
			UserStatus status = UserStatus.BLOCKED;
			statement.setString(1, status.toString());
			statement.setLong(2, id);
			int rowCount = statement.executeUpdate();
			isDeleted = (rowCount != 0);
		} catch (ConnectionPoolException | SQLException e) {
			log.error("exception while deleting user", e);
			throw new DaoException("database error", e);
		}
		return isDeleted;
	}

	@Override
	public boolean create(User t) throws DaoException {
		throw new UnsupportedOperationException("operation not supported for class " + this.getClass().getName());
	}
}
