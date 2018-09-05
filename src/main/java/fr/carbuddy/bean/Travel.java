package fr.carbuddy.bean;

import java.util.Date;

import fr.carbuddy.enumeration.TravelStatus;

public class Travel {
	private Long id;
	private User buddy;
	private User driver;
	private Address rendezVous;
	private Address terminus;
	private TravelStatus travelStatus;
	private Date date;
	/**
	 * Can not overflow 1000 (user better pay
	 * for a plane/train ticket)
	 */
	private Float price;
	
	public User getBuddy() {
		return buddy;
	}
	
	public User getDriver() {
		return driver;
	}
	
	public Address getRendezVous() {
		return rendezVous;
	}
	
	public Address getTerminus() {
		return terminus;
	}
	
	public TravelStatus getTravelStatus() {
		return travelStatus;
	}
	
	public Date getDate() {
		return date;
	}
	
	public float getPrice() {
		return price;
	}
	
	public void setBuddy(User buddy) {
		this.buddy = buddy;
	}
	
	public void setDriver(User driver) {
		this.driver = driver;
	}
	
	public void setRendezVous(Address rendezVous) {
		this.rendezVous = rendezVous;
	}
	
	public void setTerminus(Address terminus) {
		this.terminus = terminus;
	}
	
	public void setTravelStatus(TravelStatus travelStatus) {
		this.travelStatus = travelStatus;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public void setPrice(Float price) {
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
