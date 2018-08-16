package fr.carbuddy.dao;

import fr.carbuddy.bean.Address;
import fr.carbuddy.exception.DAORuntimeException;

public interface AddressDAO {

    public void create(Address address) throws DAORuntimeException;
    
    public Address findById(Long id) throws DAORuntimeException;
    
    public boolean updateAddress(Address addressToUpdate, Address newAddress) throws DAORuntimeException;
    
    public boolean deleteAddress(Address addressToDelete) throws DAORuntimeException;
    
}
