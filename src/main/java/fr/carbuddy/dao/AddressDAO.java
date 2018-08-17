package fr.carbuddy.dao;

import fr.carbuddy.bean.Address;
import fr.carbuddy.exception.DAORuntimeException;

public interface AddressDAO {

    public Address create(Address address) throws DAORuntimeException;
    
    public Address findById(Long id) throws DAORuntimeException;
    
    public boolean updateAddress(Address addressToUpdate, Address newAddress) throws DAORuntimeException;
    
    public boolean deleteAddress(Address addressToDelete) throws DAORuntimeException;
    
    /** Will retrieve the address if it already exists in database */
    public Address getAddress(Address addressproperties) throws DAORuntimeException;

    /** Will retrieve the address if it already exists in database */
    public Address getAddress(
    	String country,
		String city,
		String postal,
		String street
	) throws DAORuntimeException;
}
