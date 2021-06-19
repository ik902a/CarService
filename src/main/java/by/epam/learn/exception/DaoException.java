package by.epam.learn.exception;

/**
 * The {@code DaoException} class describes the exception in DAO
 * 
 * @author Ihar Klepcha
 * @see Exception
 */
public class DaoException extends Exception {
	private static final long serialVersionUID = 1L;

	public DaoException() {
	}

	public DaoException(String message, Throwable cause) {
		super(message, cause);
	}

	public DaoException(String message) {
		super(message);
	}

	public DaoException(Throwable cause) {
		super(cause);
	}
}
