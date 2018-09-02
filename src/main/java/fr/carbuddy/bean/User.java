package fr.carbuddy.bean;

import fr.carbuddy.enumeration.string.StatusUser;

public class User extends Person {
	
	private Long id;
	private StatusUser statusUser;
	private String username;
	private String password;
	private String avatar;
	private String paypalID;
	/**
	 * +1 If travel succeeded (max 100)
	 * -1 If travel is fooled  (max -100)
	 */
	private int karma;
	/** Anybody can be a buddy */
	private BuddyProfile buddyProfile;
	/** Anybody can be a driver (must have a vehicle) */
	private DriverProfile driverProfile;

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

	public String getAvatar() {
		return avatar;
	}

	public String getPaypalID() {
		return paypalID;
	}

	public int getKarma() {
		return karma;
	}

	public BuddyProfile getBuddyProfile() {
		return buddyProfile;
	}

	public DriverProfile getDriverProfile() {
		return driverProfile;
	}

	public void setPaypalID(String paypalID) {
		this.paypalID = paypalID;
	}

	public void setKarma(int karma) {
		this.karma = karma;
	}

	public void setBuddyProfile(BuddyProfile buddyProfile) {
		this.buddyProfile = buddyProfile;
	}

	public void setDriverProfile(DriverProfile driverProfile) {
		this.driverProfile = driverProfile;
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

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

}
