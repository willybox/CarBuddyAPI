package fr.carbuddy.validation;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import org.joda.time.DateTime;
import org.junit.Test;

import fr.carbuddy.bean.User;
import fr.carbuddy.enumeration.ValidationStatus;

public class ComplexValidation {

	@Test
	public void not_full_user_triggers_all_error() {
		User sut = new User();
		UserValidation valid = new UserValidation(sut);
		Set<ValidationStatus> result = valid.checkValidity();
		assertEquals(8, result.size());
	}
	
	@Test
	public void weak_password_error_PASSWORD_NOT_COMPLEX() {
		User sut = new User();
		sut.setPassword("123");
		UserValidation valid = new UserValidation(sut);
		
		boolean result = valid
			.checkValidity()
			.contains(ValidationStatus.PASSWORD_NOT_COMPLEX);
		assertEquals(true, result);
	}
	
	@Test
	public void password_no_lower_case_error_PASSWORD_NOT_COMPLEX() {
		User sut = new User();
		sut.setPassword("24543DSADASDIJH");
		UserValidation valid = new UserValidation(sut);
		
		boolean result = valid
			.checkValidity()
			.contains(ValidationStatus.PASSWORD_NOT_COMPLEX);
		assertEquals(true, result);
	}
	
	@Test
	public void password_no_upper_case_PASSWORD_NOT_COMPLEX() {
		User sut = new User();
		sut.setPassword("asdasdasd45151ads54");
		UserValidation valid = new UserValidation(sut);
		
		boolean result = valid
			.checkValidity()
			.contains(ValidationStatus.PASSWORD_NOT_COMPLEX);
		assertEquals(true, result);
	}
	
	@Test
	public void password_no_digit_error_PASSWORD_NOT_COMPLEX() {
		User sut = new User();
		sut.setPassword("asdsadDSADASDIJHf");
		UserValidation valid = new UserValidation(sut);
		
		boolean result = valid
			.checkValidity()
			.contains(ValidationStatus.PASSWORD_NOT_COMPLEX);
		assertEquals(true, result);
	}
	
	@Test
	public void password_ok() {
		User sut = new User();
		sut.setPassword("asdOJDSas7");
		UserValidation valid = new UserValidation(sut);
		
		boolean result = valid
			.checkValidity()
			.contains(ValidationStatus.PASSWORD_NOT_COMPLEX);
		assertEquals(false, result);
	}
	
	@Test
	public void password_start_non_alphanumeric_weak_error_PASSWORD_NOT_COMPLEX() {
		User sut = new User();
		sut.setPassword("_aaaaaaaaaaaaa");
		UserValidation valid = new UserValidation(sut);
		
		boolean result = valid
			.checkValidity()
			.contains(ValidationStatus.PASSWORD_NOT_COMPLEX);
		assertEquals(true, result);
	}

	@Test
	public void bad_phone_number_get_error_PHONE_NOT_ONLY_NUMBERS() {
		User sut = new User();
		sut.setPhone("01468754A1");
		UserValidation valid = new UserValidation(sut);
		boolean result = valid
			.checkValidity()
			.contains(ValidationStatus.PHONE_NOT_ONLY_NUMBERS);
		assertEquals(true, result);
	}

	@Test
	public void bad_phone_number_get_error_PHONE_BAD_FORMAT() {
		User sut = new User();
		sut.setPhone("4587963201");
		UserValidation valid = new UserValidation(sut);
		boolean result = valid
			.checkValidity()
			.contains(ValidationStatus.PHONE_BAD_FORMAT);
		assertEquals(true, result);
	}

	@Test
	public void bad_phone_number_get_error_PHONE_BAD_LENGTH() {
		User sut = new User();
		sut.setPhone("041598");
		UserValidation valid = new UserValidation(sut);
		boolean result = valid
			.checkValidity()
			.contains(ValidationStatus.PHONE_BAD_LENGTH);
		assertEquals(true, result);
	}

	@Test
	public void bad_phone_number_starter_get_error_PHONE_BAD_FORMAT() {
		User sut = new User();
		sut.setPhone("0325641897");
		UserValidation valid = new UserValidation(sut);
		boolean result = valid
			.checkValidity()
			.contains(ValidationStatus.PHONE_BAD_FORMAT);
		assertEquals(true, result);
	}

	@Test
	public void phone_number_01_get_no_phone_error() {
		User sut = new User();
		sut.setPhone("0123456789");
		UserValidation valid = new UserValidation(sut);
		Set<ValidationStatus> toCheck = valid.checkValidity();
		boolean result = toCheck.contains(ValidationStatus.PHONE_BAD_FORMAT)
			|| toCheck.contains(ValidationStatus.PHONE_BAD_LENGTH)
			|| toCheck.contains(ValidationStatus.PHONE_EMPTY)
			|| toCheck.contains(ValidationStatus.PHONE_NOT_ONLY_NUMBERS)
		;
		assertEquals(false, result);
	}

	@Test
	public void phone_number_06_get_no_phone_error() {
		User sut = new User();
		sut.setPhone("0623456789");
		UserValidation valid = new UserValidation(sut);
		Set<ValidationStatus> toCheck = valid.checkValidity();
		boolean result = toCheck.contains(ValidationStatus.PHONE_BAD_FORMAT)
			|| toCheck.contains(ValidationStatus.PHONE_BAD_LENGTH)
			|| toCheck.contains(ValidationStatus.PHONE_EMPTY)
			|| toCheck.contains(ValidationStatus.PHONE_NOT_ONLY_NUMBERS)
		;
		assertEquals(false, result);
	}

	@Test
	public void phone_number_07_get_no_phone_error() {
		User sut = new User();
		sut.setPhone("0723456789");
		UserValidation valid = new UserValidation(sut);
		Set<ValidationStatus> toCheck = valid.checkValidity();
		boolean result = toCheck.contains(ValidationStatus.PHONE_BAD_FORMAT)
			|| toCheck.contains(ValidationStatus.PHONE_BAD_LENGTH)
			|| toCheck.contains(ValidationStatus.PHONE_EMPTY)
			|| toCheck.contains(ValidationStatus.PHONE_NOT_ONLY_NUMBERS)
		;
		assertEquals(false, result);
	}

	@Test
	public void validation_birthday_beyond_today_error_BIRTHDAY_FUTURE() {
		User sut = new User();
		sut.setBirthday(new DateTime(2040, 6, 12, 0, 0).toDate());
		UserValidation valid = new UserValidation(sut);
		Set<ValidationStatus> toCheck = valid.checkValidity();
		boolean result = toCheck.contains(ValidationStatus.BIRTHDAY_FUTURE);
		assertEquals(true, result);
	}

	@Test
	public void validation_birthday_too_young_error_BIRTHDAY_MINOR() {
		User sut = new User();
		sut.setBirthday(new DateTime(2017, 6, 12, 0, 0).toDate());
		UserValidation valid = new UserValidation(sut);
		Set<ValidationStatus> toCheck = valid.checkValidity();
		boolean result = toCheck.contains(ValidationStatus.BIRTHDAY_MINOR);
		assertEquals(true, result);
	}

	@Test
	public void validation_birthday_ok() {
		User sut = new User();
		sut.setBirthday(new DateTime(2018, 6, 12, 0, 0).toDate());
		UserValidation valid = new UserValidation(sut);
		Set<ValidationStatus> toCheck = valid.checkValidity();
		boolean result = toCheck.contains(ValidationStatus.BIRTHDAY_NULL)
		;
		assertEquals(false, result);
	}
}
