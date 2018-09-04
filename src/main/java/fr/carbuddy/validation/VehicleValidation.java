package fr.carbuddy.validation;

import java.util.Set;

import fr.carbuddy.bean.Vehicle;
import fr.carbuddy.enumeration.ValidationStatus;
import fr.carbuddy.exception.NotValidException;
import util.library.add.on.string.AddOnString;

public class VehicleValidation implements IValidation {

	private Vehicle vehicle;

	public VehicleValidation(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	/*
	 * 
	private String brand;
	private String model;
	private int totalPlaces;(non-Javadoc)
	 */

	private ValidationStatus validationTotalPlaces() {
		if(vehicle.getTotalPlaces() <= 1) {
			return ValidationStatus.TOTAL_PLACES_NOT_ENOUGH;
		}
		return ValidationStatus.OK;
	}

	private ValidationStatus validationModel() {
		if(AddOnString.isNullOrEmpty(vehicle.getModel())) {
			return ValidationStatus.MODEL_EMPTY;
		}
		return ValidationStatus.OK;
	}

	private ValidationStatus validationBrand() {
		if(AddOnString.isNullOrEmpty(vehicle.getBrand())) {
			return ValidationStatus.BRAND_EMPTY;
		}
		return ValidationStatus.OK;
	}
	
	@Override
	public boolean checkValidity() throws NotValidException {
		NotValidException exceptionValidation = new NotValidException();
		Set<ValidationStatus> listErrors = exceptionValidation.getErrorsValidation();
		listErrors.add(validationBrand());
		listErrors.add(validationModel());
		listErrors.add(validationTotalPlaces());
		
		/** Removing OK because it is not an error */
		listErrors.remove(ValidationStatus.OK);
		
		if(!listErrors.isEmpty()) {
			throw exceptionValidation;
		}
		return listErrors.isEmpty();
	}

}
