package fr.carbuddy.dao.mysql.impl;

import static fr.carbuddy.global.ConstantValues.BRAND;
import static fr.carbuddy.global.ConstantValues.MODEL;
import static fr.carbuddy.global.ConstantValues.TOTAL_PLACES;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.carbuddy.bean.Vehicle;
import fr.carbuddy.dao.DAOFactory;
import fr.carbuddy.dao.mysql.AbstractVehicleDAOMySQL;
import fr.carbuddy.exception.DAORuntimeException;
import fr.carbuddy.exception.NotValidException;
import fr.carbuddy.global.GlobalValues;
import fr.carbuddy.validation.VehicleValidation;
import util.library.add.on.sql.AddOnSQL;

public class VehicleDAOMySQLImpl extends AbstractVehicleDAOMySQL {

	public VehicleDAOMySQLImpl(DAOFactory daoFactory) {
		super(daoFactory);
	}

	@Override
	public Vehicle create(Vehicle vehicleToCreate) throws DAORuntimeException, NotValidException {
		//TODO To test
		/** Will throw exception if not valid */
		new VehicleValidation(vehicleToCreate).checkValidity();
		
		Connection connection = daoFactory.getConnection();
		PreparedStatement pStatement = null;
        ResultSet resultSet = null;
        try {
        	StringBuilder reqStr = new StringBuilder()
	        	.append("INSERT INTO vehicle(")
		        	.append(BRAND).append(", ")
		        	.append(MODEL).append(", ")
		        	.append(TOTAL_PLACES).append(", ")
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
        			vehicleToCreate.getBrand(),
        			vehicleToCreate.getModel(),
        			vehicleToCreate.getTotalPlaces()
        		);
        	int successCount = pStatement.executeUpdate();
        	
            if(successCount > 0) {
	            ResultSet vehicleRS = pStatement.getGeneratedKeys();
	            if(vehicleRS.next()) {
	            	vehicleToCreate.setId(vehicleRS.getLong(1));
	            } else {
	            	throw new DAORuntimeException("Vehicle creation failed, no auto-generated ID returned.");
	            }
            } else {
            	throw new DAORuntimeException("Vehicle creation failed.");
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

        return vehicleToCreate;
	}

	@Override
	public Vehicle get(long id) throws DAORuntimeException {
		//TODO To test
		Connection connection = daoFactory.getConnection();
		Vehicle vehicle = null;
        PreparedStatement pStatement = null;
        ResultSet resultSet = null;
        try {
        	
        	StringBuilder reqStr = new StringBuilder()
	        	.append("SELECT * ")
	        	.append("FROM vehicle ")
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
                vehicle = getVehicleFromResultSet(resultSet);
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

        return vehicle;
	}

}
