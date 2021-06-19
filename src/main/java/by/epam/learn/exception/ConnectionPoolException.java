package by.epam.learn.exception;

/**
 * The {@code ConnectionPoolException} class describes the exception in connection pool
 * 
 * @author Ihar Klepcha
 * @see Exception
 */
public class ConnectionPoolException extends Exception {
	private static final long serialVersionUID = 1L;

	public ConnectionPoolException() {
	}

	public ConnectionPoolException(String message, Throwable cause) {
		super(message, cause);
	}

	public ConnectionPoolException(String message) {
		super(message);
	}

	public ConnectionPoolException(Throwable cause) {
		super(cause);
	}	
}
