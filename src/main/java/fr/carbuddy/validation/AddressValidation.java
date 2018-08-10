package fr.carbuddy.validation;

import java.util.HashSet;
import java.util.Set;

import fr.carbuddy.bean.Address;
import fr.carbuddy.enumeration.ValidationStatus;
import util.library.add.on.string.AddOnString;

public class AddressValidation implements IValidation {

	private Address address;

	public AddressValidation(Address address) {
		this.address = address;
	}

	private ValidationStatus validationCountry() {
		if(AddOnString.isNullOrEmpty(address.getCountry())) {
			return ValidationStatus.COUNTRY_EMPTY;
		}
		return ValidationStatus.OK;
	}

	private ValidationStatus validationCity() {
		if(AddOnString.isNullOrEmpty(address.getCity())) {
			return ValidationStatus.CITY_EMPTY;
		}
		return ValidationStatus.OK;
	}

	private ValidationStatus validationPostal() {
		if(AddOnString.isNullOrEmpty(address.getPostal())) {
			return ValidationStatus.POSTAL_EMPTY;
		}
		return ValidationStatus.OK;
	}

	private ValidationStatus validationStreet() {
		if(AddOnString.isNullOrEmpty(address.getStreet())) {
			return ValidationStatus.STREET_EMPTY;
		}
		return ValidationStatus.OK;
	}

	@Override
	public Set<ValidationStatus> checkValidity() {
		Set<ValidationStatus> listErrors = new HashSet<>();
		listErrors.add(validationCountry());
		listErrors.add(validationCity());
		listErrors.add(validationPostal());
		listErrors.add(validationStreet());
		
		/** Removing OK because it is not an error */
		listErrors.remove(ValidationStatus.OK);
		
		return listErrors;
	}

}
