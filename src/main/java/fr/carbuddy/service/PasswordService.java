package fr.carbuddy.service;

import fr.carbuddy.enumeration.ValidationStatus;

public class PasswordService {
	
	public ValidationStatus isNewPasswordSameAsConfirmation(
		String newPassword,
		String confirmPW
	) {
		if(newPassword != null && newPassword.equals(confirmPW)) {
			return ValidationStatus.PASSWORD_NOT_SAME;
		}
		return ValidationStatus.OK;
	}

}
