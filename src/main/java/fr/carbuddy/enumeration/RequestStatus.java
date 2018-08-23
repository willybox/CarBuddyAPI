package fr.carbuddy.enumeration;

import fr.carbuddy.global.GlobalValues;

public enum RequestStatus {
	
	/** Should never be displayed... */
	USER_BY_USERNAME_NOT_FOUND(
		"There is no associated user with this username",
		"Il n'y a aucun utilisateur avec ce nom d'utilisateur"
	)
	;

	private String text;
	private String frenchText;

	private RequestStatus(String val) {
		this.text = val;
		frenchText = "";
	}

	private RequestStatus(String val, String french) {
		this.text = val;
		frenchText = french;
	}
	
	public String toString() {
		switch(GlobalValues.language) {
			case FR:
				return frenchText;
			default:
				return text;
		}
	}
}
