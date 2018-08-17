package fr.carbuddy.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import fr.carbuddy.bean.Address;

public abstract class AbstractAddressDAO implements AddressDAO {
	/**
	 * Simple méthode utilitaire permettant de faire la correspondance (le
	 * mapping) entre une ligne issue de la table des utilisateurs (un
	 * ResultSet) et un bean Utilisateur.
	 * @param resultSet
	 * @return
	 * @throws SQLException
	 */
	protected static Address getAddressFromResultSet(ResultSet resultSet)
	throws SQLException {
		
		Address address = new Address();
		address.setId(resultSet.getLong("id"));
        
        /** -- Address properties -- */
    	String country = resultSet.getString("country");
        String city = resultSet.getString("city");
        String postal = resultSet.getString("postal");
        String street = resultSet.getString("street");

        address.setCountry(country);
        address.setCity(city);
        address.setPostal(postal);
        address.setStreet(street);
        return address;
	}
}
