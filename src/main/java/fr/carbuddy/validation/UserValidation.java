package fr.carbuddy.validation;

import java.util.HashSet;
import java.util.Set;

import fr.carbuddy.bean.User;
import fr.carbuddy.enumeration.ValidationStatus;
import util.library.add.on.string.AddOnString;

public class UserValidation implements IValidation {
	
	private User user;

	public UserValidation(User user) {
		this.user = user;
	}

	private ValidationStatus validationPassword() {
		if(AddOnString.isNullOrEmpty(user.getPassword())) {
			return ValidationStatus.PASSWORD_EMPTY;
		}
		/** Checking if has a number and has an upper case character */
		if(user.getPassword().length() <= 7) {
			return ValidationStatus.PASSWORD_NOT_COMPLEX;
		}
		boolean containsDigit = false;
		boolean containsLowercase = false;
		boolean containsUppercase = false;
		boolean isComplex = false;
		
		for(int i = 0; i<user.getPassword().length(); i++) {
			char c = user.getPassword().charAt(i);
			if(!containsDigit) {
				containsDigit = Character.isDigit(c);
			}
			if(!containsLowercase) {
				containsLowercase = Character.isLowerCase(c);
			}
			if(!containsUppercase) {
				containsUppercase = Character.isUpperCase(c);
			}
			isComplex = containsDigit && containsLowercase && containsUppercase;
			if(isComplex) {
				break;
			}
		}
		if(!isComplex) {
			return ValidationStatus.PASSWORD_NOT_COMPLEX;
		}
		
		return ValidationStatus.OK;
	}

	private ValidationStatus validationUsername() {
		if(AddOnString.isNullOrEmpty(user.getUsername())) {
			return ValidationStatus.USERNAME_EMPTY;
		}
		return ValidationStatus.OK;
	}
	
	private Set<ValidationStatus> validationPerson() {
		return new PersonValidation(user).checkValidity();
	}

	@Override
	public Set<ValidationStatus> checkValidity() {
		Set<ValidationStatus> listErrors = new HashSet<>();
		listErrors.add(validationPassword());
		listErrors.add(validationUsername());
		listErrors.addAll(validationPerson());
		
		/** Removing OK because it is not an error */
		listErrors.remove(ValidationStatus.OK);
		
		return listErrors;
	}

}
