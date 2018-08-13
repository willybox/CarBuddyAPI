package fr.carbuddy.service;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import fr.carbuddy.bean.Address;
import fr.carbuddy.bean.User;
import fr.carbuddy.enumeration.ValidationStatus;
import fr.carbuddy.enumeration.string.StatusUser;
import fr.carbuddy.validation.AddressValidation;
import fr.carbuddy.validation.UserValidation;

public class CreationService {
	
	private Set<ValidationStatus> errorsValidation = new HashSet<>();
	
	public User createUser(
		String username,
		String password,
		String confirmPW,
		String email,
		String name,
		String firstname,
		String phone,
		Date birthDate,
		Address adress
	) {

		User newUser = new User();
		newUser.setAddress(adress);
		newUser.setBirthday(birthDate);
		newUser.setEmail(email);
		newUser.setFirstname(firstname);
		newUser.setName(name);
		newUser.setPhone(phone);
		newUser.setPassword(password);
		newUser.setStatusUser(StatusUser.BUDDY);
		newUser.setUserName(username);
		
		/** Clearing old errors */
		errorsValidation = new UserValidation(newUser).checkValidity();
		
		ValidationStatus passwordsMatchStatus = new PasswordService(password)
			.isNewPasswordSameAsConfirmation(confirmPW);
		if(passwordsMatchStatus != ValidationStatus.OK) {
			/** Adding new error if found */
			errorsValidation.add(passwordsMatchStatus);
		}
		
		if(errorsValidation.isEmpty()) {
			//TODO Persistence
			
			return newUser;
		}
		return null;
	}
	
	public Address createAddress(
		String city,
		String country,
		String postal,
		String street
	) {

		Address address = new Address();
		address.setCity(city);
		address.setCountry(country);
		address.setPostal(postal);
		address.setStreet(street);

		/** Clearing old errors */
		errorsValidation = new AddressValidation(address).checkValidity();
		if(errorsValidation.isEmpty()) {
			//TODO Persistence
			
			return address;
		}
		return null;
	}

	public Set<ValidationStatus> getErrorsValidation() {
		return errorsValidation;
	}

}
