package fr.carbuddy.validation;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import fr.carbuddy.bean.Person;
import fr.carbuddy.enumeration.ValidationStatus;
import fr.carbuddy.exception.NotValidException;
import util.library.add.on.date.AddOnDate;
import util.library.add.on.string.AddOnString;

public class PersonValidation implements IValidation {

	private Person person;

	public PersonValidation(Person person) {
		this.person = person;
	}
	
	private ValidationStatus validationName() {
		if(AddOnString.isNullOrEmpty(person.getName())) {
			return ValidationStatus.NAME_EMPTY;
		}
		return ValidationStatus.OK;
	}
	
	private ValidationStatus validationFirstname() {
		if(AddOnString.isNullOrEmpty(person.getFirstname())) {
			return ValidationStatus.FIRSTNAME_EMPTY;
		}
		return ValidationStatus.OK;
	}
	
	private ValidationStatus validationEmail() {
		if(AddOnString.isNullOrEmpty(person.getEmail())) {
			return ValidationStatus.EMAIL_EMPTY;
		}
		return ValidationStatus.OK;
	}
	
	private ValidationStatus validationPhone() {
		if(AddOnString.isNullOrEmpty(person.getPhone())) {
			return ValidationStatus.PHONE_EMPTY;
		}
		
		if(person.getPhone().length() != 10) {
			return ValidationStatus.PHONE_BAD_LENGTH;
		}
		
		if(person.getPhone().matches(".*\\D.*")) {
			return ValidationStatus.PHONE_NOT_ONLY_NUMBERS;
		}
		
		if(person.getPhone().charAt(0) != '0') {
			return ValidationStatus.PHONE_BAD_FORMAT;
		}
		return ValidationStatus.OK;
	}
	
	private ValidationStatus validationBirthday() {
		if(person.getBirthday() == null) {
			return ValidationStatus.BIRTHDAY_NULL;
		}
		if(person.getBirthday().getTime() > new Date().getTime()) {
			return ValidationStatus.BIRTHDAY_FUTURE;
		}
		if(AddOnDate.getAge(person.getBirthday()) < 18) {
			return ValidationStatus.BIRTHDAY_MINOR;
		}
		return ValidationStatus.OK;
	}
	
	private Set<ValidationStatus> validationAddress() {
		Set<ValidationStatus> set = new HashSet<>();
		if(person.getAddress() == null) {
			set.add(ValidationStatus.ADDRESS_NULL);
			return set;
		}
		try {
			new AddressValidation(person.getAddress()).checkValidity();
		} catch (NotValidException e) {
			return e.getErrorsValidation();
		}
		return set;
	}

	@Override
	public boolean checkValidity() throws NotValidException {
		NotValidException exceptionValidation = new NotValidException();
		Set<ValidationStatus> listErrors = exceptionValidation.getErrorsValidation();
		listErrors.add(validationName());
		listErrors.add(validationFirstname());
		listErrors.add(validationEmail());
		listErrors.add(validationPhone());
		listErrors.add(validationBirthday());
		listErrors.addAll(validationAddress());
		
		/** Removing OK because it is not an error */
		listErrors.remove(ValidationStatus.OK);
		
		if(!listErrors.isEmpty()) {
			throw exceptionValidation;
		}
		
		return true;
	}

}
