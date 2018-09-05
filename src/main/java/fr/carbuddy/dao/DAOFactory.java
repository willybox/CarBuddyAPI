package fr.carbuddy.dao;

import java.sql.Connection;

public interface DAOFactory {
	
	public AddressDAO getAddressDAO();
	
	public BuddyProfileDAO getBuddyProfileDAO();
	
	public DriverProfileDAO getDriverProfileDAO();
	
	public UserDAO getUserDAO();
	
	public VehicleDAO getVehicleDAO();
	
	public Connection getConnection();
}