package fr.carbuddy.bean;

public class Vehicle {
	private Long id;
	private String brand;
	private String model;
	private int totalPlaces;
	
	public String getBrand() {
		return brand;
	}
	
	public String getModel() {
		return model;
	}
	
	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	public void setModel(String model) {
		this.model = model;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getTotalPlaces() {
		return totalPlaces;
	}

	public void setTotalPlaces(int totalPlaces) {
		this.totalPlaces = totalPlaces;
	}

}
