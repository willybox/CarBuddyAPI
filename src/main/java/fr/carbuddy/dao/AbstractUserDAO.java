package fr.carbuddy.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import fr.carbuddy.bean.User;
import fr.carbuddy.enumeration.Gender;
import fr.carbuddy.enumeration.string.StatusUser;

public abstract class AbstractUserDAO implements UserDAO {
	/**
	 * Simple méthode utilitaire permettant de faire la correspondance (le
	 * mapping) entre une ligne issue de la table des utilisateurs (un
	 * ResultSet) et un bean Utilisateur.
	 * @param resultSet
	 * @return
	 * @throws SQLException
	 */
	protected static User getUserFromResultSet(ResultSet resultSet) throws SQLException {
		User user = new User();
		user.setId(resultSet.getLong("id"));
        
        /** -- Abstract Person properties -- */

        Gender genderEnum = Gender.getEnum(resultSet.getInt("gender"));
        user.setGender(genderEnum);
        user.setFirstname(resultSet.getString("firstname"));
        user.setName(resultSet.getString("name"));
        user.setEmail(resultSet.getString("email"));
        user.setPhone(resultSet.getString("phone"));
        
        /** -- User properties -- */
        
        int statusUser = resultSet.getInt("statusUser");
        StatusUser statusUserEnum;
        switch(statusUser) {
        	case 1:
        		statusUserEnum = StatusUser.DRIVER;
        		break;
        	default:
        		statusUserEnum = StatusUser.BUDDY;
        }
        user.setStatusUser(statusUserEnum);

        user.setUsername(resultSet.getString("username"));
        user.setPassword(resultSet.getString("password"));
	    return user;
	}
}
