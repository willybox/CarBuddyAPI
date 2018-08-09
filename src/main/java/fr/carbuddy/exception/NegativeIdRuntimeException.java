package fr.carbuddy.exception;

import fr.carbuddy.enumeration.string.Message;

public class NegativeIdRuntimeException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public NegativeIdRuntimeException() {
		super(Message.NEGATIVE_ID_EXCEPTION.toString());
	}
}
