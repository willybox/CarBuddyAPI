package fr.carbuddy.enumeration.order.by;

import fr.carbuddy.global.ConstantValues;

public enum UserOrderBy {
	ID_USER("user.id"),
	ID_PERSON(ConstantValues.PERSON_ID),
	NAME(ConstantValues.NAME),
	FIRSTNAME(ConstantValues.FIRSTNAME),
	USERNAME(ConstantValues.USER_NAME),
	GENDER(ConstantValues.GENDER),
	ID_ADDRESS(ConstantValues.ADDRESS_ID),
	EMAIL(ConstantValues.E_MAIL),
	PHONE(ConstantValues.PHONE),
	BIRTHDAY(ConstantValues.BIRTHDAY)
	;
	
	private String text;

	private UserOrderBy(String val) {
		this.text = val;
	}
	
	public String toString() {
		return text;
	}
}
