package fr.carbuddy.validation;

import java.util.Date;
import java.util.Set;

import fr.carbuddy.bean.DriverProfile;
import fr.carbuddy.enumeration.ValidationStatus;
import fr.carbuddy.exception.NotValidException;

public class DriverProfileValidation implements IValidation {

	private DriverProfile driverProfile;

	public DriverProfileValidation(DriverProfile driverProfile) {
		this.driverProfile = driverProfile;
	}

	@Override
	public boolean checkValidity() throws NotValidException {
		NotValidException exceptionValidation = new NotValidException();
		Set<ValidationStatus> listErrors = exceptionValidation.getErrorsValidation();

		listErrors.add(validationMaxBuddies());
		listErrors.add(validationTraveDate());

		/** Removing OK because it is not an error */
		listErrors.remove(ValidationStatus.OK);
		
		/** Note: for now, there is no mandatory values for DriverProfile */
		
		if(!listErrors.isEmpty()) {
			throw exceptionValidation;
		}
		return true;
	}

	private ValidationStatus validationMaxBuddies() {
		if(driverProfile.getMaxBuddies() <= 0) {
			return ValidationStatus.MAX_BUDDIES_NEGATIVE;
		}
		if(driverProfile.getMaxBuddies() > driverProfile.getVehicle().getTotalPlaces() - 1) {
			return ValidationStatus.MAX_BUDDIES_OVER_BOOKED;
		}
		return ValidationStatus.OK;
	}

	private ValidationStatus validationTraveDate() {
		/** Date can be undetermined */
		if(driverProfile.getTravelDate() != null) {
			/** If date refers to past, not valid */
			if(driverProfile.getTravelDate().before(new Date())) {
				return ValidationStatus.TRAVEL_DATE_PAST;
			}
		}
		return ValidationStatus.OK;
	}

}
