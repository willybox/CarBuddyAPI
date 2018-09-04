package fr.carbuddy.bean;

import java.util.Date;

public class BuddyProfile {
	private Long id;
	private Address start;
	private Address destination;
	private Date travelDate;
	
	public Address getStart() {
		return start;
	}
	
	public Address getDestination() {
		return destination;
	}
	
	public Date getTravelDate() {
		return travelDate;
	}
	
	public void setStart(Address start) {
		this.start = start;
	}
	
	public void setDestination(Address destination) {
		this.destination = destination;
	}
	
	public void setTravelDate(Date travelDate) {
		this.travelDate = travelDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
