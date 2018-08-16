package fr.carbuddy.exception;

public class DAORuntimeException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
    public DAORuntimeException(String message) {
        super(message);
    }

    public DAORuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public DAORuntimeException(Throwable cause) {
        super(cause);
    }
}