package fr.carbuddy.dao.mysql.impl;

import static fr.carbuddy.global.ConstantValues.BUDDY;
import static fr.carbuddy.global.ConstantValues.DATE;
import static fr.carbuddy.global.ConstantValues.DRIVER;
import static fr.carbuddy.global.ConstantValues.RENDEZ_VOUS;
import static fr.carbuddy.global.ConstantValues.TERMINUS;
import static fr.carbuddy.global.ConstantValues.TRAVEL_STATUS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.carbuddy.bean.Travel;
import fr.carbuddy.bean.User;
import fr.carbuddy.dao.DAOFactory;
import fr.carbuddy.dao.mysql.AbstractTravelDAOMySQL;
import fr.carbuddy.exception.DAORuntimeException;
import fr.carbuddy.exception.NotValidException;
import fr.carbuddy.global.GlobalValues;
import fr.carbuddy.validation.TravelValidation;
import util.library.add.on.sql.AddOnSQL;

public class TravelDAOMySQLImpl extends AbstractTravelDAOMySQL {

	public TravelDAOMySQLImpl(DAOFactory daoFactory) {
		super(daoFactory);
	}

	@Override
	public Travel create(Travel travelToCreate) throws DAORuntimeException, NotValidException {
		//TODO To test
		/** Will throw exception if not valid */
		new TravelValidation(travelToCreate).checkValidity();
		
		Connection connection = daoFactory.getConnection();
		PreparedStatement pStatement = null;
        ResultSet resultSet = null;
        try {
        	StringBuilder reqStr = new StringBuilder()
	        	.append("INSERT INTO travel(")
		        	.append(BUDDY).append(", ")
		        	.append(DRIVER).append(", ")
		        	.append(RENDEZ_VOUS).append(", ")
		        	.append(TERMINUS).append(", ")
		        	.append(TRAVEL_STATUS).append(", ")
		        	.append(DATE).append(", ")
	        	.append(") ")
	        	.append("VALUES(?, ?)")
	        	.append(";")
        	;
        	/** Creating requests manager */
        	pStatement = AddOnSQL
        		.initPreparedStatement(
    				connection,
    				reqStr.toString(),
        			true,
        			travelToCreate.getBuddy().getId(),
        			travelToCreate.getDriver().getId(),
        			travelToCreate.getRendezVous().getId(),
        			travelToCreate.getTerminus().getId(),
        			travelToCreate.getTravelStatus().getValue(),
        			travelToCreate.getDate()
        		);
        	int successCount = pStatement.executeUpdate();
        	
            if(successCount > 0) {
	            ResultSet travelRS = pStatement.getGeneratedKeys();
	            if(travelRS.next()) {
	            	travelToCreate.setId(travelRS.getLong(1));
	            } else {
	            	throw new DAORuntimeException("Travel creation failed, no auto-generated ID returned.");
	            }
            } else {
            	throw new DAORuntimeException("Travel creation failed.");
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

        return travelToCreate;
	}

	@Override
	public Travel get(long id) throws DAORuntimeException {
		//TODO To test
		Connection connection = daoFactory.getConnection();
		Travel travel = null;
        PreparedStatement pStatement = null;
        ResultSet resultSet = null;
        try {
        	
        	StringBuilder reqStr = new StringBuilder()
	        	.append("SELECT * ")
	        	.append("FROM travel ")
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
                travel = getTravelFromResultSet(resultSet);
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

        return travel;
	}

	@Override
	public List<Travel> listTravelHistoryUser(User user, boolean asc) {
		List<Travel> travelsFromUser = new ArrayList<>();
		Connection connection = daoFactory.getConnection();
        PreparedStatement pStatement = null;
        ResultSet resultSet = null;
        try {
        	
        	StringBuilder reqStr = new StringBuilder()
	        	.append("SELECT * ")
	        	.append("FROM travel ")
	        	.append("WHERE driver=? ")
	        	.append("OR buddy=? ")
	        	.append("ORDER BY date ").append(asc ? "ASC" : "DESC")
	        	.append(";")
        	;
        	
        	System.out.print("Request \"" + reqStr.toString());
        	
            /** Creating requests manager */
        	pStatement = AddOnSQL
        		.initPreparedStatement(
    				connection,
        			reqStr.toString(),
        			false,
        			user.getId(),
        			user.getId()
        		);
            
            /** Executing SELECT */
            resultSet = pStatement.executeQuery();
            System.out.println("\" done.");
     
            /** Retrieving data from result set */
            while(resultSet.next()) {
                travelsFromUser.add(getTravelFromResultSet(resultSet));
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

        return travelsFromUser;
	}

	@Override
	public boolean updateTravel(Travel travelToUpdate, Travel newTravel) throws DAORuntimeException {
		//TODO To test
		Connection connection = daoFactory.getConnection();
		PreparedStatement pStatement = null;
        ResultSet resultSet = null;
        boolean updated = false;
        try {
        	StringBuilder reqStr = new StringBuilder()
	        	.append("UPDATE driverprofile ")
	        	.append("SET ")
	        	.append(BUDDY).append("=? ")
	        	.append(DRIVER).append("=?, ")
	        	.append(RENDEZ_VOUS).append("=?, ")
	        	.append(TERMINUS).append("=?, ")
	        	.append(TRAVEL_STATUS).append("=?, ")
	        	.append(DATE).append("=? ")
	        	.append("WHERE id=?")
	        	.append(";")
        	;
        	/** Creating requests manager */
        	pStatement = AddOnSQL
        		.initPreparedStatement(
    				connection,
    				reqStr.toString(),
        			true,
        			newTravel.getBuddy().getId(),
        			newTravel.getDriver().getId(),
        			newTravel.getRendezVous().getId(),
        			newTravel.getTerminus().getId(),
        			newTravel.getTravelStatus().getValue(),
        			newTravel.getDate(),
    				travelToUpdate.getId()
        		);
        	int successCount = pStatement.executeUpdate();
        	
        	updated = successCount > 0;
            if(!updated) {
            	throw new DAORuntimeException("Travel update failed.");
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
	public boolean deleteTravel(Travel travelToDelete) throws DAORuntimeException {
		//TODO TO test
		Connection connection = daoFactory.getConnection();
        PreparedStatement pStatement = null;
        ResultSet resultSet = null;
        try {
        	
        	StringBuilder reqPersonStr = new StringBuilder()
	        	.append("DELETE FROM travel ")
	        	.append("WHERE id=?")
	        	.append(";")
        	;
        	/** Creating requests manager */
        	PreparedStatement personPreparedStatement = AddOnSQL
        		.initPreparedStatement(
    				connection,
    				reqPersonStr.toString(),
        			true,
        			travelToDelete.getId()
        		);
        	int personSuccessCount = personPreparedStatement.executeUpdate();
        	if(personSuccessCount > 0) {
        		System.out.println(
        			"Successfully removed travel from database."
        		);
        	} else {
        		System.out.println("Travel with id "
        			+ travelToDelete.getId()
        			+ " not found in database. Deletion failed."
        		);
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

		return true;
	}

}
