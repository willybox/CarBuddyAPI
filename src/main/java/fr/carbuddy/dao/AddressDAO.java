package fr.carbuddy.dao;

import java.util.List;

import fr.carbuddy.bean.Address;
import fr.carbuddy.exception.DAORuntimeException;
import fr.carbuddy.exception.NotValidException;

public interface AddressDAO {

	/**
	 * Will create a new Address row if not exists, else
	 * it returns the existing address in database
	 * @param address
	 * @return
	 * @throws DAORuntimeException
	 * @throws NotValidException
	 */
    public Address create(Address address) throws DAORuntimeException, NotValidException;
    
    public Address findById(Long id) throws DAORuntimeException;
    
    public boolean deleteAddress(Address addressToDelete) throws DAORuntimeException;
    
    /** For auto completion */
    public List<String> listCountry();

    /** For auto completion */
    public List<String> listPostal();

    /** For auto completion */
    public List<String> listCity();

    /** For auto completion */
    public List<String> listStreet();
    
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
