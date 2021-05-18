package by.epam.learn.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.learn.entity.User;
import by.epam.learn.entity.UserRole;
import by.epam.learn.entity.UserStatus;
import by.epam.learn.exception.DaoException;

import by.epam.learn.model.dao.UserDao;
import by.epam.learn.model.pool.ConnectionPool;
import by.epam.learn.util.PasswordEncryptor;

import static by.epam.learn.model.dao.ColumnName.*;


public class UserDaoImpl implements UserDao {
	public static Logger log = LogManager.getLogger();
	private static final UserDao instance = new UserDaoImpl();
	private static final String SQL_CONTAINS_LOGIN = "SELECT 1 FROM users WHERE login=?";
	private static final String SQL_ADD_USER = "INSERT INTO users(login, name, email, password,"
			+ "role, status) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String SQL_ACTIVATE_CLIENT = "UPDATE users SET status=? WHERE login=?";
	
	private static final String SQL_FIND_USER = "SELECT iduser, login, name, email, password, phone,"
			+ "role, status FROM users WHERE login=?";
	private static final String SQL_UPDATE_CLIENT = "UPDATE users SET login=?, name=?, email=?,"
			+ "phone=? WHERE iduser=?";
	
	private static final String SQL_FIND_MECHANIC = "SELECT iduser, login, name, email, password, phone,"
			+ "role, status FROM users WHERE role=mechanic";
	
	
//	private static final String SQL_SELECT_ALL_USERS = "SELECT id, login, name FROM users";
//	private static final String SQL_SELECT_USERS_BY_NAME = "SELECT id, login, name FROM users WHERE name like ?";
//	private static final String SQL_SELECT_ALL_NAMES = "SELECT name FROM users";

	private UserDaoImpl() {
	}

	public static UserDao getInstance() {
		return instance;
	}

	@Override
	public boolean containsLogin(String login) throws DaoException {
		boolean isContains = false;
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_CONTAINS_LOGIN)) {
			log.debug("Dao - constaintlogin=" + login);
			statement.setString(1, login);
			ResultSet resultSet = statement.executeQuery();
			log.debug("Dao - resultset=" + resultSet.toString());
			if (resultSet.next()) {
				isContains = true;
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		return isContains;
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
			UserRole role = user.getRole();
			UserStatus status = user.getStatus();
			statement.setString(1, login);
			statement.setString(2, name);
			statement.setString(3, email);
			statement.setString(4, hashedPassword);
			statement.setString(5, role.toString());
			statement.setString(6, status.toString());
			int rowCount = statement.executeUpdate();
			if (rowCount != 0) {
				isAdded = true;
				log.info("User added successfully");
			} else {
				log.error("User {} wasn't added", user);
				isAdded = false;
			}
		} catch (SQLException e) {
			log.error("Exception while adding new user", e);
			throw new DaoException("Exception while adding new user", e);
		}
		return isAdded;
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

			if (rowCount != 0) {
				isActivated = true;
				log.info("User activated successfully");
			} else {
				log.error("User {} wasn't activated", login);
				isActivated = false;
			}
		} catch (SQLException e) {
			log.error("Exception while adding new user", e);
			throw new DaoException("Exception while adding new user", e);
		}
		return isActivated;
	}

	@Override
	public Optional<User> findUser(String login, String password) throws DaoException {
		   Optional<User> optionalUser = Optional.empty();
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
	        } catch (SQLException e) {
	            throw new DaoException(e);
	        }
	        return optionalUser;
	}
	
	@Override
	public boolean updateClient(User user) throws DaoException {
		boolean isUpdate;
		try (Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_CLIENT)) {
			long userId = user.getUserId();
			String login = user.getLogin();
			log.debug("Dao - login=" + login);
			String name = user.getName();
			String email = user.getEmail();
			String phone = user.getPhone();
			
			statement.setString(1, login);
			statement.setString(2, name);
			statement.setString(3, email);
			statement.setString(4, phone);
			statement.setLong(5, userId);

			int rowCount = statement.executeUpdate();

			if (rowCount != 0) {
				isUpdate = true;
				log.info("User added successfully");
			} else {
				log.error("User {} wasn't added", user);
				isUpdate = false;
			}
		} catch (SQLException e) {
			log.error("Exception while adding new user", e);
			throw new DaoException("Exception while adding new user", e);
		}
		return isUpdate;
	}
	
	@Override
	public Optional<User> findMechanic() throws DaoException {
		   Optional<User> mechanics = Optional.empty();
	        try (Connection connection = ConnectionPool.getInstance().getConnection();
	             PreparedStatement statement = connection.prepareStatement(SQL_FIND_MECHANIC)) {
	            ResultSet resultSet = statement.executeQuery();
	            
				while (resultSet.next()) {
					User mechanic = DaoEntityBuilder.buildUser(resultSet);
					mechanics = Optional.of(mechanic);
				}
	        } catch (SQLException e) {
	            throw new DaoException(e);
	        }
	        return mechanics;
	}
	

	
	
	
	@Override
	public List<User> findAll() throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findEntityById(Integer id) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(User t) throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Integer id) throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean create(User t) throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(User t) throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}




}
