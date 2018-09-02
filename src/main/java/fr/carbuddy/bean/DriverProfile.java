package fr.carbuddy.bean;

import java.util.Date;

public class DriverProfile {
	/** Max buddy in vehicle */
	private int maxBuddies;
	private Vehicle vehicle;
	private int numberLuggages;
	private Address preferedStart;
	private Address destination;
	private Date travelDate;
	
	public int getMaxBuddies() {
		return maxBuddies;
	}
	
	public Vehicle getVehicle() {
		return vehicle;
	}
	
	public int getNumberLuggages() {
		return numberLuggages;
	}
	
	public Address getPreferedStart() {
		return preferedStart;
	}
	
	public Address getDestination() {
		return destination;
	}
	
	public Date getTravelDate() {
		return travelDate;
	}
	
	public void setMaxBuddies(int maxBuddies) {
		this.maxBuddies = maxBuddies;
	}
	
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	
	public void setNumberLuggages(int numberLuggages) {
		this.numberLuggages = numberLuggages;
	}
	
	public void setPreferedStart(Address preferedStart) {
		this.preferedStart = preferedStart;
	}
	
	public void setDestination(Address destination) {
		this.destination = destination;
	}
	
	public void setTravelDate(Date travelDate) {
		this.travelDate = travelDate;
	}
}
