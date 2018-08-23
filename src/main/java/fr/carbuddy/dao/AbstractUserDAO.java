package fr.carbuddy.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import fr.carbuddy.bean.User;
import fr.carbuddy.enumeration.Gender;
import fr.carbuddy.enumeration.string.StatusUser;

public abstract class AbstractUserDAO implements UserDAO {
	/**
	 * Utility function allowing to match columns from user
	 * table and bean properties
	 * @param resultSet
	 * @return
	 * @throws SQLException
	 */
	protected static User getUserFromResultSet(ResultSet resultSet) throws SQLException {
		User user = new User();
		user.setId(resultSet.getLong("id"));
        
        /** -- Abstract Person properties -- */

		user.setPersonId(resultSet.getLong("personId"));
        user.setName(resultSet.getString("name"));
        user.setFirstname(resultSet.getString("firstname"));
        Gender genderEnum = Gender.getEnum(resultSet.getInt("gender"));
        user.setGender(genderEnum);
        /** Address must be set outside this function (Append it) */
        user.setEmail(resultSet.getString("email"));
        user.setPhone(resultSet.getString("phone"));
        user.setBirthday(resultSet.getDate("birthday"));
        
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
        user.setAvatar(resultSet.getString("avatar"));
	    return user;
	}
}
