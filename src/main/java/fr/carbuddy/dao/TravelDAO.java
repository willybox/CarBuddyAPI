package fr.carbuddy.dao;

import java.util.List;

import fr.carbuddy.bean.Travel;
import fr.carbuddy.bean.User;
import fr.carbuddy.exception.DAORuntimeException;
import fr.carbuddy.exception.NotValidException;

public interface TravelDAO {

    public Travel create(Travel travel) throws DAORuntimeException, NotValidException;
   
    /**
     * Will retrieve all travel history from user.
     * Automatically ordered by date.
     * @param asc
     * @return
     */
    public List<Travel> listTravelHistoryUser(User user, boolean asc);

    public Travel get(long id) throws DAORuntimeException;
    
    public boolean updateTravel(Travel travelToUpdate, Travel newTravel) throws DAORuntimeException;
    
    public boolean deleteTravel(Travel travelToDelete) throws DAORuntimeException;

}
