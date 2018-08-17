package fr.carbuddy.bean;

import fr.carbuddy.enumeration.string.StatusUser;

public class User extends Person {
	
	private Long id;
	private StatusUser statusUser;
	private String username;
	private String password;

	public StatusUser getStatusUser() {
		return statusUser;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public Long getId() {
		return id;
	}

	public void setStatusUser(StatusUser statusUser) {
		this.statusUser = statusUser;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
