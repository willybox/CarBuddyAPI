package fr.carbuddy.dao;

import fr.carbuddy.bean.DriverProfile;
import fr.carbuddy.exception.DAORuntimeException;
import fr.carbuddy.exception.NotValidException;

public interface DriverProfileDAO {

    public DriverProfile create(DriverProfile driverProfile) throws DAORuntimeException, NotValidException;

    public DriverProfile get(long id) throws DAORuntimeException;
    
    public boolean updateDriverProfile(DriverProfile driverProfileToUpdate, DriverProfile newDriverProfile) throws DAORuntimeException;
    
    public boolean deleteDriverProfile(DriverProfile driverProfileToDelete) throws DAORuntimeException;

}
