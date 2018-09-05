package fr.carbuddy.exception;

import java.util.HashSet;
import java.util.Set;

import fr.carbuddy.enumeration.ValidationStatus;

public class NotValidException extends Exception {
	private static final long serialVersionUID = 1L;
	private Set<ValidationStatus> errorsValidation = new HashSet<>();

	public NotValidException() {
		super("Operation failed, check thisException.getErrorsValidation() in order to get reasons of failures");
	}

	public Set<ValidationStatus> getErrorsValidation() {
		return errorsValidation;
	}
}
