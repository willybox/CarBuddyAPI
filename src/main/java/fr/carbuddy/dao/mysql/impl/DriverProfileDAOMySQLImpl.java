package fr.carbuddy.dao.mysql.impl;

import static fr.carbuddy.global.ConstantValues.DESTINATION;
import static fr.carbuddy.global.ConstantValues.MAX_BUDDIES;
import static fr.carbuddy.global.ConstantValues.NUMBER_LUGGAGES;
import static fr.carbuddy.global.ConstantValues.PREFERED_START;
import static fr.carbuddy.global.ConstantValues.TRAVEL_DATE;
import static fr.carbuddy.global.ConstantValues.VEHICLE_ID;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.carbuddy.bean.DriverProfile;
import fr.carbuddy.dao.DAOFactory;
import fr.carbuddy.dao.mysql.AbstractDriverProfileDAOMySQL;
import fr.carbuddy.exception.DAORuntimeException;
import fr.carbuddy.exception.NotValidException;
import fr.carbuddy.global.GlobalValues;
import fr.carbuddy.validation.DriverProfileValidation;
import util.library.add.on.sql.AddOnSQL;

public class DriverProfileDAOMySQLImpl extends AbstractDriverProfileDAOMySQL {

	public DriverProfileDAOMySQLImpl(DAOFactory daoFactory) {
		super(daoFactory);
	}

	@Override
	public DriverProfile create(DriverProfile driverProfileToCreate) throws DAORuntimeException, NotValidException {
		/** Will throw exception if not valid */
		new DriverProfileValidation(driverProfileToCreate).checkValidity();
		
		Connection connection = daoFactory.getConnection();
		PreparedStatement pStatement = null;
        ResultSet resultSet = null;
        try {
        	StringBuilder reqStr = new StringBuilder()
	        	.append("INSERT INTO driverprofile(")
		        	.append(MAX_BUDDIES).append(", ")
		        	.append(NUMBER_LUGGAGES).append(", ")
		        	.append(TRAVEL_DATE).append(", ")
		        	.append(PREFERED_START).append(", ")
		        	.append(DESTINATION).append(", ")
		        	.append(VEHICLE_ID)
	        	.append(") ")
	        	.append("VALUES(?, ?, ?, ?, ?, ?)")
	        	.append(";")
        	;
        	/** Creating requests manager */
        	pStatement = AddOnSQL
        		.initPreparedStatement(
    				connection,
    				reqStr.toString(),
        			true,
        			driverProfileToCreate.getMaxBuddies(),
        			driverProfileToCreate.getNumberLuggages(),
        			driverProfileToCreate.getTravelDate(),
        			driverProfileToCreate.getPreferedStart(),
        			driverProfileToCreate.getDestination() == null
        				? null
        				: driverProfileToCreate.getDestination().getId(),
        			driverProfileToCreate.getVehicle().getId()
        		);
        	int successCount = pStatement.executeUpdate();
        	
            if(successCount > 0) {
	            ResultSet driverProfileRS = pStatement.getGeneratedKeys();
	            if(driverProfileRS.next()) {
	            	driverProfileToCreate.setId(driverProfileRS.getLong(1));
	            } else {
	            	throw new DAORuntimeException("Driver profile creation failed, no auto-generated ID returned.");
	            }
            } else {
            	throw new DAORuntimeException("Driver profile creation failed.");
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

        return driverProfileToCreate;
	}

	@Override
	public DriverProfile get(long id) throws DAORuntimeException {
		//TODO To test
		Connection connection = daoFactory.getConnection();
		DriverProfile driverProfile = null;
        PreparedStatement pStatement = null;
        ResultSet resultSet = null;
        try {
        	
        	StringBuilder reqStr = new StringBuilder()
	        	.append("SELECT * ")
	        	.append("FROM driverprofile ")
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
                driverProfile = getDriverProfileFromResultSet(resultSet);
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

        return driverProfile;
	}

	@Override
	public boolean updateDriverProfile(DriverProfile driverProfileToUpdate, DriverProfile newDriverProfile)
			throws DAORuntimeException {
		//TODO To test
		Connection connection = daoFactory.getConnection();
		PreparedStatement pStatement = null;
        ResultSet resultSet = null;
        boolean updated = false;
        try {
        	StringBuilder reqStr = new StringBuilder()
	        	.append("UPDATE driverprofile ")
	        	.append("SET ")
	        	.append(MAX_BUDDIES).append("=? ")
	        	.append(NUMBER_LUGGAGES).append("=?, ")
	        	.append(TRAVEL_DATE).append("=?, ")
	        	.append(PREFERED_START).append("=?, ")
	        	.append(DESTINATION).append("=?, ")
	        	.append(VEHICLE_ID).append("=? ")
	        	.append("WHERE id=?")
	        	.append(";")
        	;
        	/** Creating requests manager */
        	pStatement = AddOnSQL
        		.initPreparedStatement(
    				connection,
    				reqStr.toString(),
        			true,
        			newDriverProfile.getMaxBuddies(),
        			newDriverProfile.getNumberLuggages(),
        			newDriverProfile.getTravelDate(),
        			newDriverProfile.getPreferedStart(),
        			newDriverProfile.getDestination() == null
        				? null
        				: newDriverProfile.getDestination().getId(),
    				newDriverProfile.getVehicle().getId(),
        			driverProfileToUpdate.getId()
        		);
        	int successCount = pStatement.executeUpdate();
        	
        	updated = successCount > 0;
            if(!updated) {
            	throw new DAORuntimeException("Driver profile update failed.");
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
	public boolean deleteDriverProfile(DriverProfile driverProfileToDelete) throws DAORuntimeException {
		//TODO To test
		Connection connection = daoFactory.getConnection();
		PreparedStatement pStatement = null;
        ResultSet resultSet = null;
        boolean deleted = false;
        try {
        	StringBuilder reqStr = new StringBuilder()
	        	.append("DELETE FROM driverprofile")
	        	.append("WHERE id=?")
	        	.append(";")
        	;
        	/** Creating requests manager */
        	pStatement = AddOnSQL
        		.initPreparedStatement(
    				connection,
    				reqStr.toString(),
        			true,
        			driverProfileToDelete.getId()
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
