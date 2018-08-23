package fr.carbuddy.service;

import static fr.carbuddy.enumeration.ValidationStatus.AVATAR_NOT_IMAGE;
import static fr.carbuddy.enumeration.ValidationStatus.AVATAR_NOT_READABLE;
import static fr.carbuddy.enumeration.ValidationStatus.AVATAR_NULL;
import static fr.carbuddy.enumeration.ValidationStatus.INVALID_USERNAME_OR_PASSWORD;
import static fr.carbuddy.enumeration.ValidationStatus.OK;
import static fr.carbuddy.enumeration.ValidationStatus.PASSWORD_TOO_MUCH_TRY;

import java.io.InputStream;

import fr.carbuddy.bean.User;
import fr.carbuddy.enumeration.ValidationStatus;
import util.library.add.on.encryption.AddOnEncryption;
import util.library.add.on.file.AddOnFile;

public class UserService {

	private User user;

	public UserService(User user) {
		this.user = user;
	}
	
	public ValidationStatus checkPasswordAuthentication(
		String passInput,
		int numberTryOfDay
	) {
		if(numberTryOfDay > 7) {
			return PASSWORD_TOO_MUCH_TRY;
		}
		
		boolean matches = false;
		try {
		matches = AddOnEncryption.checkMatching(passInput, user.getPassword());
		} catch (Exception e) {
			/** Do nothing, let the boolean "matches" as false
			 * Side note: If EncryptionOperationNotPossibleException
			 * is triggered, it means that in the database, the registered
			 * password is either null, empty or badly formatted. In this case,
			 * the user should reset password
			 */
		}
		
		return matches
			? OK
			: INVALID_USERNAME_OR_PASSWORD;
	}
	
	public ValidationStatus checkAvatar(InputStream inputStream) {
		if(inputStream == null) {
			return AVATAR_NULL;
		}
		try {
			if(!AddOnFile.isImageBasedOnMime(inputStream)) {
				return AVATAR_NOT_IMAGE;
			}
		} catch (Exception e) {
			return AVATAR_NOT_READABLE;
		}

		return OK;
	}
	
	public User createCopy() {
		User copy = new User();
		
		/** -- User -- */
		copy.setId(user.getId());
		copy.setStatusUser(user.getStatusUser());
		copy.setUsername(user.getUsername());
		copy.setPassword(user.getPassword());
		copy.setAvatar(user.getAvatar());
		
		/** -- Person -- */
		copy.setPersonId(user.getPersonId());
		copy.setName(user.getName());
		copy.setFirstname(user.getFirstname());
		copy.setGender(user.getGender());
		copy.setAddress(user.getAddress());
		copy.setEmail(user.getEmail());
		copy.setPhone(user.getPhone());
		copy.setBirthday(user.getBirthday());
		
		return copy;
	}
}
