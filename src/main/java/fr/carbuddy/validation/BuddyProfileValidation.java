package fr.carbuddy.validation;

import java.util.Date;
import java.util.Set;

import fr.carbuddy.bean.BuddyProfile;
import fr.carbuddy.enumeration.ValidationStatus;
import fr.carbuddy.exception.NotValidException;

public class BuddyProfileValidation implements IValidation {

	private BuddyProfile buddyProfile;

	public BuddyProfileValidation(BuddyProfile buddyProfile) {
		this.buddyProfile = buddyProfile;
	}

	private ValidationStatus validateTravelDate() {
		/** Date can be undetermined */
		if(buddyProfile.getTravelDate() != null) {
			/** If date refers to past, not valid */
			if(buddyProfile.getTravelDate().before(new Date())) {
				return ValidationStatus.TRAVEL_DATE_PAST;
			}
		}
		return ValidationStatus.OK;
	}

	@Override
	public boolean checkValidity() throws NotValidException {
		NotValidException exceptionValidation = new NotValidException();
		Set<ValidationStatus> listErrors = exceptionValidation.getErrorsValidation();
		
		listErrors.add(validateTravelDate());

		/** Removing OK because it is not an error */
		listErrors.remove(ValidationStatus.OK);
		
		/** Note: for now, there is no mandatory values for BuddyProfile */
		
		if(!listErrors.isEmpty()) {
			throw exceptionValidation;
		}
		return true;
	}

}
