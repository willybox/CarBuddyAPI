package fr.carbuddy.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import fr.carbuddy.bean.Address;
import fr.carbuddy.exception.DAORuntimeException;

public abstract class AbstractAddressDAO implements AddressDAO {
	/**
	 * Utility function allowing to match columns from address
	 * table and bean properties
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

	@Override
	public Address getAddress(String country, String city, String postal, String street) throws DAORuntimeException {
		Address dummyAddress = new Address();
		dummyAddress.setCity(city);
		dummyAddress.setCountry(country);
		dummyAddress.setPostal(postal);
		dummyAddress.setStreet(street);
		return getAddress(dummyAddress);
	}
}
