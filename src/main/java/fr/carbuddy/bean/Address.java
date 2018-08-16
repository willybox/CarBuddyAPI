package fr.carbuddy.bean;

public class Address {
	
	private Long id;
	private String country;
	private String city;
	private String postal;
	private String street;

	public String getCountry() {
		return country;
	}

	public String getCity() {
		return city;
	}

	public String getPostal() {
		return postal;
	}

	public String getStreet() {
		return street;
	}

	public Long getId() {
		return id;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setPostal(String postal) {
		this.postal = postal;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
