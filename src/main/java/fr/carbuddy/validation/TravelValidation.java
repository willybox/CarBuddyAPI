package fr.carbuddy.validation;

import java.util.Date;
import java.util.Set;

import fr.carbuddy.bean.Travel;
import fr.carbuddy.enumeration.ValidationStatus;
import fr.carbuddy.exception.NotValidException;

public class TravelValidation implements IValidation {
	private Travel travel;

	public TravelValidation(Travel travel) {
		this.travel = travel;
	}

	private ValidationStatus validationBuddy() {
		if(travel.getBuddy() == null) {
			return ValidationStatus.BUDDY_NULL;
		}
		return ValidationStatus.OK;
	}

	private ValidationStatus validationDriver() {
		if(travel.getDriver() == null) {
			return ValidationStatus.DRIVER_NULL;
		}
		return ValidationStatus.OK;
	}

	@Override
	public boolean checkValidity() throws NotValidException {
		NotValidException exceptionValidation = new NotValidException();
		Set<ValidationStatus> listErrors = exceptionValidation.getErrorsValidation();
		listErrors.add(validationBuddy());
		listErrors.add(validationDriver());
		listErrors.add(validationRendezVous());
		listErrors.add(validationTerminus());
		listErrors.add(validationDate());
		
		/** Removing OK because it is not an error */
		listErrors.remove(ValidationStatus.OK);
		
		if(!listErrors.isEmpty()) {
			throw exceptionValidation;
		}
		return listErrors.isEmpty();
	}

	private ValidationStatus validationDate() {
		if(travel.getDate() == null) {
			return ValidationStatus.DATE_NULL;
		}
		/** If date refers to past, not valid */
		if(travel.getDate().before(new Date())) {
			return ValidationStatus.TRAVEL_DATE_PAST;
		}
		return ValidationStatus.OK;
	}

	private ValidationStatus validationTerminus() {
		if(travel.getTerminus() == null) {
			return ValidationStatus.TERMINUS_NULL;
		}
		return ValidationStatus.OK;
	}

	private ValidationStatus validationRendezVous() {
		if(travel.getRendezVous() == null) {
			return ValidationStatus.RENDEZ_VOUS_NULL;
		}
		return ValidationStatus.OK;
	}
}
