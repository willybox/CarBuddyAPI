package fr.carbuddy.bean;

import fr.carbuddy.enumeration.string.StatusUser;

public class User extends Person {
	
	private StatusUser statusUser;

	public StatusUser getStatusUser() {
		return statusUser;
	}

	public void setStatusUser(StatusUser statusUser) {
		this.statusUser = statusUser;
	}

}
