package fr.carbuddy.enumeration;

import fr.carbuddy.global.GlobalValues;

public enum ValidationStatus {
	ADDRESS_NULL(
		"Error: Address is null",
		"Erreur: L'adresse est nulle"
	),
	
	BIRTHDAY_NULL(
		"Error: birthday is null. How sad... :(",
		"Erreur: la date de naissance est nulle. Que c'est triste... :("
	),
	
	BIRTHDAY_FUTURE(
		"Time traveling is illegal buddy",
		"Le voyage dans le temps est ill�gal mon pote"
	),
	
	BIRTHDAY_MINOR(
		"You must be 18 in order to use this application",
		"Vous devez avoir 18 ans pour utiliser cette application"
	),
	
	CITY_EMPTY(
		"Please specify your city",
		"Veuillez pr�ciser votre ville"
	),
	
	COUNTRY_EMPTY(
		"Please select your country",
		"Veuillez s�lectionner votre pays"
	),
	
	EMAIL_EMPTY(
		"The e-mail can not be empty",
		"L'e-mail ne peut pas �tre vide"
	),
	
	FIRSTNAME_EMPTY(
		"The firstname can not be empty",
		"Le pr�nom ne peut pas �tre vide"
	),
	
	NAME_EMPTY(
		"The name can not be empty",
		"Le nom ne peut pas �tre vide"
	),
	
	PASSWORD_EMPTY(
		"The password can not be empty",
		"Le mot de passe ne peut pas �tre vide"
	),
	
	PASSWORD_NOT_COMPLEX(
		"The password must contain an upper case character, a number and must have at least 7 characters",
		"Ce mot de passe est faible, veuillez en �crire un avec au moins 7 caract�res, une majuscule et un chiffre"
	),
	
	PHONE_BAD_FORMAT(
		"Correct phone format is the french one: 10 digits, starting with 01, 06 or 07",
		"Le bon format de num�ro de t�lephone est celui de France � 10 chiffres commen�ant par 01, 06 ou 07"
	),
	
	PHONE_BAD_LENGTH(
		"The phone number length must be equal to 10",
		"Le num�ro de t�l�phone doit comporter 10 chiffres"
	),
	
	PHONE_EMPTY(
		"The phone number can not be empty",
		"Le num�ro de t�l�phone ne peut pas �tre vide"
	),
	
	PHONE_NOT_ONLY_NUMBERS(
		"The phone number can not contains non digit caracters",
		"Le num�ro de t�l�phone ne doit comporter que des chiffres"
	),
	
	POSTAL_EMPTY(
		"Please specify your postal code",
		"Veuillez pr�ciser votre code postal"
	),
	
	STREET_EMPTY(
		"Please specify your street",
		"Veuillez pr�ciser votre rue"
	),
	
	USERNAME_EMPTY(
		"Username cannot be empty",
		"Votre nom d'utilisateur ne peut pas �tre vide"
	),
	
	OK("")
	;

	private String text;
	private String frenchText;

	private ValidationStatus(String val) {
		this.text = val;
		frenchText = "";
	}

	private ValidationStatus(String val, String french) {
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
