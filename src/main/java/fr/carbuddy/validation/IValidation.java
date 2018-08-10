package fr.carbuddy.validation;

import java.util.Set;

import fr.carbuddy.enumeration.ValidationStatus;

public interface IValidation {
	public Set<ValidationStatus> checkValidity();
}
