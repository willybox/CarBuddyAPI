package fr.carbuddy.dao.mysql.impl;

import static fr.carbuddy.global.ConstantValues.DESTINATION;
import static fr.carbuddy.global.ConstantValues.START;
import static fr.carbuddy.global.ConstantValues.TRAVEL_DATE;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.carbuddy.bean.BuddyProfile;
import fr.carbuddy.dao.DAOFactory;
import fr.carbuddy.dao.mysql.AbstractBuddyProfileDAOMySQL;
import fr.carbuddy.exception.DAORuntimeException;
import fr.carbuddy.exception.NotValidException;
import fr.carbuddy.global.GlobalValues;
import fr.carbuddy.validation.BuddyProfileValidation;
import util.library.add.on.sql.AddOnSQL;

public class BuddyProfileDAOMySQLImpl extends AbstractBuddyProfileDAOMySQL {

	public BuddyProfileDAOMySQLImpl(DAOFactory daoFactory) {
		super(daoFactory);
	}

	@Override
	public BuddyProfile create(BuddyProfile buddyProfileToCreate) throws DAORuntimeException, NotValidException {
		/** Will throw exception if not valid */
		new BuddyProfileValidation(buddyProfileToCreate).checkValidity();
		
		Connection connection = daoFactory.getConnection();
		PreparedStatement pStatement = null;
        ResultSet resultSet = null;
        try {
        	StringBuilder reqStr = new StringBuilder()
	        	.append("INSERT INTO buddyprofile(")
		        	.append(TRAVEL_DATE).append(", ")
		        	.append(START).append(", ")
		        	.append(DESTINATION)
	        	.append(") ")
	        	.append("VALUES(?, ?, ?)")
	        	.append(";")
        	;
        	/** Creating requests manager */
        	pStatement = AddOnSQL
        		.initPreparedStatement(
    				connection,
    				reqStr.toString(),
        			true,
        			buddyProfileToCreate.getTravelDate(),
        			buddyProfileToCreate.getStart(),
        			buddyProfileToCreate.getDestination()
        		);
        	int successCount = pStatement.executeUpdate();
        	
            if(successCount > 0) {
	            ResultSet buddyProfileRS = pStatement.getGeneratedKeys();
	            if(buddyProfileRS.next()) {
	            	buddyProfileToCreate.setId(buddyProfileRS.getLong(1));
	            } else {
	            	throw new DAORuntimeException("Buddy profile creation failed, no auto-generated ID returned.");
	            }
            } else {
            	throw new DAORuntimeException("Buddy profile creation failed.");
            }
        } catch (SQLException e) {
            throw new DAORuntimeException("Error during the connection");
        } finally {
        	AddOnSQL.fancyClosures(
        		resultSet,
        		pStatement,
        		connection,
        		GlobalValues.NO_DEBUG
        	);
        }

        return buddyProfileToCreate;
	}

	@Override
	public BuddyProfile get(long id) throws DAORuntimeException {
		Connection connection = daoFactory.getConnection();
		BuddyProfile buddyProfile = null;
        PreparedStatement pStatement = null;
        ResultSet resultSet = null;
        try {
        	
        	StringBuilder reqStr = new StringBuilder()
	        	.append("SELECT * ")
	        	.append("FROM buddyprofile ")
	        	.append("WHERE id=? ")
	        	.append(";")
        	;
        	
        	System.out.print("Request \"" + reqStr.toString());
        	
            /** Creating requests manager */
        	pStatement = AddOnSQL
        		.initPreparedStatement(
    				connection,
        			reqStr.toString(),
        			false,
        			id
        		);
            
            /** Executing SELECT */
            resultSet = pStatement.executeQuery();
            System.out.println("\" done.");
     
            /** Retrieving data from result set */
            if(resultSet.next()) {
                buddyProfile = getBuddyProfileFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new DAORuntimeException("Error during the connection");
        } finally {
        	AddOnSQL.fancyClosures(
        		resultSet,
        		pStatement,
        		connection,
        		GlobalValues.NO_DEBUG
        	);
        }

        return buddyProfile;
	}

	@Override
	public boolean updateBuddyProfile(
		BuddyProfile buddyProfileToUpdate,
		BuddyProfile newBuddyProfile
	) throws DAORuntimeException {
		//TODO To test
		Connection connection = daoFactory.getConnection();
		PreparedStatement pStatement = null;
        ResultSet resultSet = null;
        boolean updated = false;
        try {
        	StringBuilder reqStr = new StringBuilder()
	        	.append("UPDATE buddyprofile ")
	        	.append("SET ")
	        	.append(DESTINATION).append("=? ")
	        	.append(START).append("=?, ")
	        	.append(TRAVEL_DATE).append("=?, ")
	        	.append("WHERE id=?")
	        	.append(";")
        	;
        	/** Creating requests manager */
        	pStatement = AddOnSQL
        		.initPreparedStatement(
    				connection,
    				reqStr.toString(),
        			true,
        			newBuddyProfile.getDestination() == null
        				? null
        				: newBuddyProfile.getDestination().getId(),
        			newBuddyProfile.getStart() == null
        				? null
        				: newBuddyProfile.getStart().getId(),
        			newBuddyProfile.getTravelDate(),
        			buddyProfileToUpdate.getId()
        		);
        	int successCount = pStatement.executeUpdate();
        	
        	updated = successCount > 0;
            if(!updated) {
            	throw new DAORuntimeException("Buddy profile update failed.");
            }
        } catch (SQLException e) {
            throw new DAORuntimeException("Error during the connection");
        } finally {
        	AddOnSQL.fancyClosures(
        		resultSet,
        		pStatement,
        		connection,
        		GlobalValues.NO_DEBUG
        	);
        }

        return updated;
	}

	@Override
	public boolean deleteBuddyProfile(BuddyProfile buddyProfileToDelete) throws DAORuntimeException {
		//TODO To test
		Connection connection = daoFactory.getConnection();
		PreparedStatement pStatement = null;
        ResultSet resultSet = null;
        boolean deleted = false;
        try {
        	StringBuilder reqStr = new StringBuilder()
	        	.append("DELETE FROM buddyprofile")
	        	.append("WHERE id=?")
	        	.append(";")
        	;
        	/** Creating requests manager */
        	pStatement = AddOnSQL
        		.initPreparedStatement(
    				connection,
    				reqStr.toString(),
        			true,
        			buddyProfileToDelete.getId()
        		);
        	int successCount = pStatement.executeUpdate();
        	
        	deleted = successCount > 0;
            if(!deleted) {
            	throw new DAORuntimeException("Buddy profile suppression failed.");
            }
        } catch (SQLException e) {
            throw new DAORuntimeException("Error during the connection");
        } finally {
        	AddOnSQL.fancyClosures(
        		resultSet,
        		pStatement,
        		connection,
        		GlobalValues.NO_DEBUG
        	);
        }

        return deleted;
	}

}
