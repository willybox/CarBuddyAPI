package fr.carbuddy.dao;

import fr.carbuddy.bean.BuddyProfile;
import fr.carbuddy.exception.DAORuntimeException;
import fr.carbuddy.exception.NotValidException;

public interface BuddyProfileDAO {

    public BuddyProfile create(BuddyProfile buddyProfile) throws DAORuntimeException, NotValidException;

    public BuddyProfile get(long id) throws DAORuntimeException;
    
    public boolean updateBuddyProfile(BuddyProfile buddyProfileToUpdate, BuddyProfile newBuddyProfile) throws DAORuntimeException;
    
    public boolean deleteBuddyProfile(BuddyProfile buddyProfileToDelete) throws DAORuntimeException;

}
