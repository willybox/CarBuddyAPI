package fr.carbuddy.dao.mysql;

import static fr.carbuddy.global.ConstantValues.DESTINATION;
import static fr.carbuddy.global.ConstantValues.START;
import static fr.carbuddy.global.ConstantValues.TRAVEL_DATE;

import java.sql.ResultSet;
import java.sql.SQLException;

import fr.carbuddy.bean.Address;
import fr.carbuddy.bean.BuddyProfile;
import fr.carbuddy.dao.BuddyProfileDAO;
import fr.carbuddy.dao.DAOFactory;

public abstract class AbstractBuddyProfileDAOMySQL implements BuddyProfileDAO {
	
	protected DAOFactory daoFactory;
	
	public AbstractBuddyProfileDAOMySQL(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	
	/**
	 * Utility function allowing to match columns from BuddyProfile
	 * table and bean properties
	 * @param resultSet
	 * @return
	 * @throws SQLException
	 */
	protected BuddyProfile getBuddyProfileFromResultSet(ResultSet resultSet) throws SQLException {
		BuddyProfile buddyProfile = new BuddyProfile();
		buddyProfile.setId(resultSet.getLong("id"));
        
		/** -- BuddyProfile properties -- */
		buddyProfile.setTravelDate(resultSet.getDate(TRAVEL_DATE));
		Long addressDestinationId = resultSet.getLong(DESTINATION);		
		Address destination = daoFactory.getAddressDAO().findById(addressDestinationId);
		buddyProfile.setDestination(destination);

		Long addressStartId = resultSet.getLong(START);		
		Address start = daoFactory.getAddressDAO().findById(addressStartId);
		buddyProfile.setStart(start);
	    return buddyProfile;
	}
}
