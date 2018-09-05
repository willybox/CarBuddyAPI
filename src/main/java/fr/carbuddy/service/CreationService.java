package fr.carbuddy.service;

import java.util.Date;
import java.util.Set;

import fr.carbuddy.bean.Address;
import fr.carbuddy.bean.User;
import fr.carbuddy.dao.DAOFactory;
import fr.carbuddy.enumeration.Gender;
import fr.carbuddy.enumeration.ValidationStatus;
import fr.carbuddy.enumeration.string.StatusUser;
import fr.carbuddy.exception.DAORuntimeException;
import fr.carbuddy.exception.NotValidException;
import util.library.add.on.encryption.AddOnEncryption;

public class CreationService {
	private DAOFactory daoFactory;
	
	public CreationService(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	
	public User createUser(
		String username,
		String password,
		String confirmPW,
		String sex,
		String email,
		String name,
		String firstname,
		String phone,
		Date birthDate,
		Address adress
	) throws NotValidException {
		NotValidException notValidException = new NotValidException();
		Set<ValidationStatus> errorsValidation = notValidException.getErrorsValidation();
		User newUser = new User();
		newUser.setAddress(adress);
		if(sex.equals("female")) {
			newUser.setGender(Gender.FEMALE);
		} else {
			newUser.setGender(Gender.MALE);
		}
		newUser.setBirthday(birthDate);
		newUser.setEmail(email);
		newUser.setFirstname(firstname);
		newUser.setName(name);
		newUser.setPhone(phone);
		newUser.setPassword(password);
		newUser.setStatusUser(StatusUser.BUDDY);
		newUser.setUsername(username);
		
		ValidationStatus passwordsMatchStatus = new PasswordService(password)
			.isNewPasswordSameAsConfirmation(confirmPW);
		if(passwordsMatchStatus != ValidationStatus.OK) {
			/** Adding new error if found */
			errorsValidation.add(passwordsMatchStatus);
		}
		
		/** Username unicity */
		if(daoFactory.getUserDAO().findByUsername(username) != null) {
			/** Adding new error if found */
			errorsValidation.add(ValidationStatus.USERNAME_ALREADY_EXISTS);
		}
		
		if(errorsValidation.isEmpty()) {
			/** Encrypting password */
		    newUser.setPassword(AddOnEncryption.encryptString(password));
		    
			/** Persisting data */
			try {
				daoFactory.getUserDAO().create(newUser);
			} catch (DAORuntimeException e) {
			} catch (NotValidException e) {
				errorsValidation.addAll(e.getErrorsValidation());
				throw e;
			}
			
			return newUser;
		}
		return null;
	}
	
	public Address createAddress(
		String city,
		String country,
		String postal,
		String street
	) throws NotValidException {

		Address address = new Address();
		address.setCity(city);
		address.setCountry(country);
		address.setPostal(postal);
		address.setStreet(street);
		
		Address existingAddress = daoFactory.getAddressDAO().getAddress(address);
		if(existingAddress != null) {
			return existingAddress;
		}

		/** Persisting data */
		daoFactory.getAddressDAO().create(address);
		
		return address;
	}

}
