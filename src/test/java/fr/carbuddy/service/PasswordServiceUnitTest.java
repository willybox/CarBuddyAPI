package fr.carbuddy.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;

import fr.carbuddy.enumeration.ValidationStatus;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

/**
 * @author Willy U.
 *
 */
@RunWith(JUnitParamsRunner.class)
public class PasswordServiceUnitTest {

	@Parameters({
		"mySecretPa$$word, mySecretPa$$word",
		"hello, hello",
		"qwerty123, qwerty123"
	})
	@Test
	public void same_passwords_returns_OK() {
		PasswordService sut = new PasswordService("hello");
		ValidationStatus result = sut.isNewPasswordSameAsConfirmation("hello");
		assertEquals(ValidationStatus.OK, result);
	}

	@Parameters({
		"mySecretPassword, maSecretPassWord",
		"UPPERCASE, UPERCASE",
		"qwerty, Qwerty",
		"passw0rd, passwOrd"
	})
	@Test
	public void different_passwords_returns_PASSWORD_NOT_SAME(String password, String confirm) {
		PasswordService sut = new PasswordService(password);
		ValidationStatus result = sut.isNewPasswordSameAsConfirmation(confirm);
		assertEquals(ValidationStatus.PASSWORD_NOT_SAME, result);
	}
}
