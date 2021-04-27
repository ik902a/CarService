package by.epam.learn.model.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mysql.cj.jdbc.Driver;

final class ConnectionFactory {
	public static Logger log = LogManager.getLogger();
	private static final ResourceBundle bundle;
	private static final String BUNDLE_NAME = "property/database";
	private static final String DB_URL = "db.url";
	private static final String DB_USER = "db.user";
	private static final String DB_PASSWORD = "db.password";
	private static final String DATABASE_URL;
	private static final String DATABASE_USER_NAME;
	private static final String DATABASE_PASSWORD;
	
	static {
		bundle = ResourceBundle.getBundle(BUNDLE_NAME);
		try {			
			DriverManager.registerDriver(new Driver());
		} catch (SQLException e) {
			log.error("ERROR driver doesn't found", e);//TODO  + driver name
			throw new RuntimeException("driver doesn't found", e);
		}
		DATABASE_URL = bundle.getString(DB_URL);
		DATABASE_USER_NAME = bundle.getString(DB_USER);
		DATABASE_PASSWORD = bundle.getString(DB_PASSWORD);
	}
	
	ConnectionFactory() {
	}

	static Connection createConnection() throws SQLException {
		return DriverManager.getConnection(DATABASE_URL, DATABASE_USER_NAME, DATABASE_PASSWORD);
	}
}
