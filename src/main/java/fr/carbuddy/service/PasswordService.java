package fr.carbuddy.service;

import fr.carbuddy.enumeration.ValidationStatus;

public class PasswordService {
	
	private String password;

	public PasswordService(String password) {
		this.password = password;
	}
	
	public ValidationStatus isNewPasswordSameAsConfirmation(
		String confirmPW
	) {
		if(password != null && !password.equals(confirmPW)) {
			return ValidationStatus.PASSWORD_NOT_SAME;
		}
		return ValidationStatus.OK;
	}

}
