package persistence.exception;

public class MyPersistenceException extends Exception {

	private static final long serialVersionUID = -1572065868219496352L;

	public MyPersistenceException() {
	}

	public MyPersistenceException(String message) {
		super(message);
	}

	public MyPersistenceException(Throwable cause) {
		super(cause);
	}

	public MyPersistenceException(String message, Throwable cause) {
		super(message, cause);
	}
}