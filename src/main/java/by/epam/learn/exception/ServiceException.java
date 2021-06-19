package by.epam.learn.exception;

/**
 * The {@code ServiceException} class describes the exception in service
 * 
 * @author Ihar Klepcha
 * @see Exception
 */
public class ServiceException extends Exception {
	private static final long serialVersionUID = 1L;

	public ServiceException() {
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}
}
