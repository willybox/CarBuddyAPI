package fr.carbuddy.enumeration;

import fr.carbuddy.global.GlobalValues;

public enum ValidationStatus {
	ADDRESS_NULL(
		"Error: Address is null",
		"Erreur: L'adresse est nulle"
	),
	
	AVATAR_BAD_USE_REQUEST(
		"This kind of request is not supported, please use adequate form in order to send your file.",
		"Ce type de requ�te n'est pas support�, merci d'utiliser le formulaire pr�vu pour envoyer votre fichier."
	),
	
	AVATAR_NOT_IMAGE(
		"The file MUST be an image.",
		"Le fichier envoy� doit �tre une image."
	),
	
	AVATAR_NOT_READABLE(
		"Seems like the avatar image is corrupted or does not exists...",
		"Il semblerait que l'avatar soit corrompue ou n'existe plus..."
	),
	
	AVATAR_NULL(
		"Error: Avatar is null",
		"Erreur: L'avatar est nul"
	),
	
	AVATAR_OVER_SIZE(
		"Le fichier envoy� ne doit pas d�passer 1Mo.",
		""
	),
	
	AVATAR_WRITE_FAILURE(
		"Error while writing file into disk.",
		"Erreur lors de l'�criture du fichier sur le disque."
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
	
	BRAND_EMPTY(
		"Please specify your vehicle brand (Ex: Fiat, Citro�n, Volkswagen...)",
		"Veuillez pr�ciser la marque de votre v�hicule (Ex: Fiat, Citro�n, Volkswagen...)"
	),
	
	BUDDY_NULL(
		"Making a travel without buddy is boring... Select one of your buddy",
		"Faire un voyage sans buddy est d'un ennui... S�lectionne-en un."
	),
	
	CITY_EMPTY(
		"Please specify your city",
		"Veuillez pr�ciser votre ville"
	),
	
	CONFIGURATION_LIMITATION(
		"Error on server configuration",
		"Erreur de configuration du serveur."
	),
	
	COUNTRY_EMPTY(
		"Please select your country",
		"Veuillez s�lectionner votre pays"
	),
	
	DATE_NULL(
		"Please specify a date",
		"Veuillez pr�ciser la date"
	),
	
	DRIVER_NULL(
		"Please ask for a driver to get you there",
		"Veulliez s�lectionner un conducteur pour vous y amener."
	),
	
	EMAIL_EMPTY(
		"The e-mail can not be empty",
		"L'e-mail ne peut pas �tre vide"
	),
	
	FIRSTNAME_EMPTY(
		"The firstname can not be empty",
		"Le pr�nom ne peut pas �tre vide"
	),
	
	INVALID_USERNAME_OR_PASSWORD(
		"You have entered an invalid username or password.",
		"Vous avez entr� le mauvais nom d'utilisateur ou mot de passe."
	),
	
	MAX_BUDDIES_NEGATIVE(
		"You must set at leat one max buddy.",
		"Vous devez mettre au moins un buddy au maximum."
	),
	
	MAX_BUDDIES_OVER_BOOKED(
		"Your vehicle can not transprot this much passengers",
		"Votre v�hicule ne peut pas transporter autant de passagers"
	),
	
	MODEL_EMPTY(
		"Please specify your vehicle model (Ex: Fiat 500, Citro�n C3...)",
		"Veuillez pr�ciser le mod�le de votre v�hicule (Ex: Fiat 500, Citro�n C3...)"
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
	
	PASSWORD_NOT_SAME(
		"The password is not the same as the confirmation",
		"Votre mot de passe est diff�rent de celui de la confirmation"
	),
	
	PASSWORD_TOO_MUCH_TRY(
		"You have tried 7 times to connect with the wrong password. Try again in 24h or request for a password reset if forgotten",
		"Vous avez tent� de vous connecter 7 fois avec de mauvais mots de passe. R�essayez dans 24h ou faites une demande r�initialisation de mot de passe si vous l'avez oubli�"
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
	
	RENDEZ_VOUS_NULL(
		"Please specify your rendez-vous (meeting) point",
		"Veuillez pr�ciser votre point de rendez-vous"
	),
	
	STREET_EMPTY(
		"Please specify your street",
		"Veuillez pr�ciser votre rue"
	),
	
	TERMINUS_NULL(
		"Please specify your terminus (destination)",
		"Veuillez pr�ciser votre terminus"
	),
	
	TOTAL_PLACES_NOT_ENOUGH(
		"The vehicle must at least contain 2 places",
		"Le v�hicule doit comporter au moins 2 places"
	),
	
	TRAVEL_DATE_PAST(
		"Doc has forbidden travelling in the past Marty!",
		"Doc nous a interdit de voyager dans le pass� Marty!"
	),
	
	USERNAME_ALREADY_EXISTS(
		"Username already exists",
		"Ce nom d'utilisateur est d�j� utilis�"
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
