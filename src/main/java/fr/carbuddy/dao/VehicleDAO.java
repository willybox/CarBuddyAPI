package fr.carbuddy.dao;

import fr.carbuddy.bean.Vehicle;
import fr.carbuddy.exception.DAORuntimeException;
import fr.carbuddy.exception.NotValidException;

public interface VehicleDAO {

    public Vehicle create(Vehicle vehicle) throws DAORuntimeException, NotValidException;

    public Vehicle get(long id) throws DAORuntimeException;
    
    /** Note: User will never update/delete, only create or switch vehicle */
}
