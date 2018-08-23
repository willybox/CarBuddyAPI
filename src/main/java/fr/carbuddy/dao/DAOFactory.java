package fr.carbuddy.dao;

import java.sql.Connection;

public interface DAOFactory {
	
	public UserDAO getUserDAO();
	
	public AddressDAO getAddressDAO();
	
	public Connection getConnection();
}