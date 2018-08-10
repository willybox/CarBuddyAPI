package fr.carbuddy.bean;

import fr.carbuddy.enumeration.string.StatusUser;

public class User extends Person {
	
	private StatusUser statusUser;
	private String userName;
	private String password;

	public StatusUser getStatusUser() {
		return statusUser;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public void setStatusUser(StatusUser statusUser) {
		this.statusUser = statusUser;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
