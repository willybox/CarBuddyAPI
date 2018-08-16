package fr.carbuddy.exception;

public class DAOConfigurationRuntimeException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
    public DAOConfigurationRuntimeException(String message) {
        super(message);
    }

    public DAOConfigurationRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public DAOConfigurationRuntimeException(Throwable cause) {
        super(cause);
    }
}