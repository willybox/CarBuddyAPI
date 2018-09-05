package fr.carbuddy.dao.mysql;

import static fr.carbuddy.global.ConstantValues.ADDRESS_ID;
import static fr.carbuddy.global.ConstantValues.AVATAR;
import static fr.carbuddy.global.ConstantValues.BIRTHDAY;
import static fr.carbuddy.global.ConstantValues.BUDDY_PROFILE;
import static fr.carbuddy.global.ConstantValues.DRIVER_PROFILE;
import static fr.carbuddy.global.ConstantValues.E_MAIL;
import static fr.carbuddy.global.ConstantValues.FIRSTNAME;
import static fr.carbuddy.global.ConstantValues.GENDER;
import static fr.carbuddy.global.ConstantValues.ID;
import static fr.carbuddy.global.ConstantValues.KARMA;
import static fr.carbuddy.global.ConstantValues.NAME;
import static fr.carbuddy.global.ConstantValues.PASSWORD;
import static fr.carbuddy.global.ConstantValues.PAYPAL_ID;
import static fr.carbuddy.global.ConstantValues.PERSON_ID;
import static fr.carbuddy.global.ConstantValues.PHONE;
import static fr.carbuddy.global.ConstantValues.STATUS_USER;
import static fr.carbuddy.global.ConstantValues.USERNAME;

import java.sql.ResultSet;
import java.sql.SQLException;

import fr.carbuddy.bean.Address;
import fr.carbuddy.bean.BuddyProfile;
import fr.carbuddy.bean.DriverProfile;
import fr.carbuddy.bean.User;
import fr.carbuddy.dao.DAOFactory;
import fr.carbuddy.dao.UserDAO;
import fr.carbuddy.enumeration.Gender;
import fr.carbuddy.enumeration.string.StatusUser;

public abstract class AbstractUserDAOMySQL implements UserDAO {
	
	protected DAOFactory daoFactory;
	
	public AbstractUserDAOMySQL(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	
	/**
	 * Utility function allowing to match columns from User
	 * table and bean properties
	 * @param resultSet
	 * @return
	 * @throws SQLException
	 */
	protected User getUserFromResultSet(ResultSet resultSet) throws SQLException {
		User user = new User();
		user.setId(resultSet.getLong(ID));
        
        /** -- Abstract Person properties -- */

		user.setPersonId(resultSet.getLong(PERSON_ID));
        user.setName(resultSet.getString(NAME));
        user.setFirstname(resultSet.getString(FIRSTNAME));
        Gender genderEnum = Gender.getEnum(resultSet.getInt(GENDER));
        user.setGender(genderEnum);
        Address address = daoFactory
        	.getAddressDAO()
        	.findById(resultSet.getLong(ADDRESS_ID));
        if(address != null) {
        	user.setAddress(address);
        }
        user.setEmail(resultSet.getString(E_MAIL));
        user.setPhone(resultSet.getString(PHONE));
        user.setBirthday(resultSet.getDate(BIRTHDAY));
        
        /** -- User properties -- */
        
        int statusUser = resultSet.getInt(STATUS_USER);
        StatusUser statusUserEnum;
        switch(statusUser) {
        	case 1:
        		statusUserEnum = StatusUser.DRIVER;
        		break;
        	default:
        		statusUserEnum = StatusUser.BUDDY;
        }
        user.setStatusUser(statusUserEnum);

        user.setUsername(resultSet.getString(USERNAME));
        user.setPassword(resultSet.getString(PASSWORD));
        user.setAvatar(resultSet.getString(AVATAR));
        user.setPaypalID(resultSet.getString(PAYPAL_ID));
        user.setKarma(resultSet.getInt(KARMA));
        BuddyProfile buddyProfile = daoFactory
        	.getBuddyProfileDAO()
        	.get(resultSet.getLong(BUDDY_PROFILE));
        
		user.setBuddyProfile(buddyProfile);
		
        DriverProfile driverProfile = daoFactory
            	.getDriverProfileDAO()
            	.get(resultSet.getLong(DRIVER_PROFILE));
            
    		user.setDriverProfile(driverProfile);
	    return user;
	}
}
