package fr.carbuddy.enumeration.string;

import fr.carbuddy.global.GlobalValues;

public enum TermsAndServices {
	BUDDY_RESPONSABILITY(
		"By accepting our terms and services, you will have the responsability to check that your driver is legally able to transport to your chosen destination in exchange of the price negotiated with your driver",
		"En acceptant nos conditions d'utilisation, vous avez la responsabilit� de v�rifier que votre conducteur est l�galement capable de vous transporter en �change du prix que vous avez n�goci� avec ce dernier."
	),
	DRIVER_RESPONSABILITY(
		"You are fully responsible of your passengers and have to respect to the driving laws of the countries you are traveling on for a secured travel. Depending of your country, you might have to declare the money you earn. You have the duty to check if your passengers are not minors by asking for any proof that shows that they are 18+ if there is any doubt",
		"Vous �tes enti�rement responsable de vos passagers et devez respecter les lois des pays dans lesquels vous conduisez pour un voyage s�curis�. Selon votre pays, il se peut que vous ayez � d�clarer les b�n�fices que vous r�alisez. Aussi, vous vous engagez � v�rifier que vos passagers ne sont pas mineurs en leur demandant, si un doute se pose, une pi�ce d'identit�."	
	),
	USER_RESPONSABILITY(
		"By accepting our terms and services, you certify to be 18+.",
		"En acceptant nos conditions d'utilisation, vous certifiez �tre ag� d'au moins 18 ans."
	),
	PERSONAL_INFORMATION_USAGE(
		"Your personal information will not be used outside the application CarBuddy",
		"Vos informations personnelles ne seront pas utilis�es en dehors de l'application CarBuddy."
	)
	;
	
	private String text;
	private String frenchText;

	private TermsAndServices(String eng) {
		this.text = eng;
	}	

	private TermsAndServices(String eng, String fr) {
		this.text = eng;
		this.frenchText = fr;
	}
	
	public String toString() {
		switch(GlobalValues.language) {
			case FR:
				return frenchText;
			default:
		
		}
		return text;
	}
}
