package fr.carbuddy.validation;

import fr.carbuddy.exception.NotValidException;

public interface IValidation {
	public boolean checkValidity() throws NotValidException;
}
