package fr.carbuddy.bean;

import java.sql.Timestamp;

import fr.carbuddy.enumeration.string.StatusUser;

public class User extends Person {
	
	private Long id;
	private StatusUser statusUser;
	private String userName;
	private String password;
    private Timestamp dateInscription;

	public StatusUser getStatusUser() {
		return statusUser;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public Timestamp getDateInscription() {
		return dateInscription;
	}

	public Long getId() {
		return id;
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

	public void setDateInscription(Timestamp dateInscription) {
		this.dateInscription = dateInscription;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
