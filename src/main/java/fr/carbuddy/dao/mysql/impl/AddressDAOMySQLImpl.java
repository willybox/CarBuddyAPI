package fr.carbuddy.dao.mysql.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.carbuddy.bean.Address;
import fr.carbuddy.dao.DAOFactory;
import fr.carbuddy.dao.mysql.AbstractAddressDAOMySQL;
import fr.carbuddy.exception.DAORuntimeException;
import fr.carbuddy.exception.NotValidException;
import fr.carbuddy.global.GlobalValues;
import fr.carbuddy.validation.AddressValidation;
import util.library.add.on.sql.AddOnSQL;

public class AddressDAOMySQLImpl extends AbstractAddressDAOMySQL {
	
	public AddressDAOMySQLImpl(DAOFactory daoFactory) {
		super(daoFactory);
	}

	@Override
	public Address create(Address addressToCreate) throws DAORuntimeException, NotValidException {
		/** Will throw exception if not valid */
		new AddressValidation(addressToCreate).checkValidity();
		
		Address existingAddress = getAddress(addressToCreate);
		if(existingAddress != null) {
			return existingAddress;
		}
		
		Connection connection = daoFactory.getConnection();
		PreparedStatement pStatement = null;
        ResultSet resultSet = null;
        try {
        	StringBuilder reqStr = new StringBuilder()
	        	.append("INSERT INTO address(country, city, postal, street) ")
	        	.append("VALUES(?, ?, ?, ?)")
	        	.append(";")
        	;
        	/** Creating requests manager */
        	pStatement = AddOnSQL
        		.initPreparedStatement(
    				connection,
    				reqStr.toString(),
        			true,
        			addressToCreate.getCountry(),
        			addressToCreate.getCity(),
        			addressToCreate.getPostal(),
        			addressToCreate.getStreet()
        		);
        	int successCount = pStatement.executeUpdate();
        	
            if(successCount > 0) {
	            ResultSet addressRS = pStatement.getGeneratedKeys();
	            if(addressRS.next()) {
	            	addressToCreate.setId(addressRS.getLong(1));
	            } else {
	            	throw new DAORuntimeException("Address creation failed, no auto-generated ID returned.");
	            }
            } else {
            	throw new DAORuntimeException("Address creation failed.");
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

        return addressToCreate;
	}

	@Override
	public boolean deleteAddress(Address addressToDelete) throws DAORuntimeException {
		//TODO To test
		Connection connection = daoFactory.getConnection();
		PreparedStatement pStatement = null;
        ResultSet resultSet = null;
        boolean deleted = false;
        try {
        	StringBuilder reqStr = new StringBuilder()
	        	.append("DELETE FROM address")
	        	.append("WHERE id=?")
	        	.append(";")
        	;
        	/** Creating requests manager */
        	pStatement = AddOnSQL
        		.initPreparedStatement(
    				connection,
    				reqStr.toString(),
        			true,
        			addressToDelete.getId()
        		);
        	int successCount = pStatement.executeUpdate();
        	
        	deleted = successCount > 0;
            if(!deleted) {
            	throw new DAORuntimeException("Address suppression failed.");
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

	@Override
	public Address findById(Long addressId) throws DAORuntimeException {
		Connection connection = daoFactory.getConnection();
		Address address = null;
        PreparedStatement pStatement = null;
        ResultSet resultSet = null;
        try {
        	StringBuilder reqStr = new StringBuilder()
    			.append("SELECT id, country, city, postal, street ")
	        	.append("FROM address ")
	        	.append("WHERE id=?")
	        	.append(";");
            System.out.println("Request \"" + reqStr.toString());
        	
            /** Creating requests manager */
            pStatement = AddOnSQL
            	.initPreparedStatement(
        			connection,
            		reqStr.toString(),
            		false,
            		addressId
            	);

            /** Executing SELECT */
            resultSet = pStatement.executeQuery();
            System.out.println("\" done.");
     
            /** Retrieving data from result set */
            if(resultSet.next()) {
            	address = getAddressFromResultSet(resultSet);
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
        return address;
	}

	@Override
	public Address getAddress(Address addressproperties) throws DAORuntimeException {
		Connection connection = daoFactory.getConnection();
        PreparedStatement pStatement = null;
        ResultSet resultSet = null;
        try {
        	StringBuilder reqStr = new StringBuilder()
    			.append("SELECT id, country, city, postal, street ")
	        	.append("FROM address ")
	        	.append("WHERE country=? ")
	        	.append("AND city=? ")
	        	.append("AND postal=? ")
	        	.append("AND street=?")
	        	.append(";");
            System.out.println("Request \"" + reqStr.toString());
        	
            /** Creating requests manager */
            pStatement = AddOnSQL
            	.initPreparedStatement(
        			connection,
            		reqStr.toString(),
            		false,
        			addressproperties.getCountry(),
        			addressproperties.getCity(),
        			addressproperties.getPostal(),
        			addressproperties.getStreet()
            	);

            /** Executing SELECT */
            resultSet = pStatement.executeQuery();
            System.out.println("\" done.");
     
            /** Retrieving data from result set */
            if(resultSet.next()) {
            	addressproperties.setId(resultSet.getLong("id"));
            } else {
            	return null;
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
        return addressproperties;
	}

	@Override
	public List<String> listCity() {
		//TODO To test
		List<String> cities = new ArrayList<>();
		Connection connection = daoFactory.getConnection();
        PreparedStatement pStatement = null;
        ResultSet resultSet = null;
        try {
        	StringBuilder reqStr = new StringBuilder()
    			.append("SELECT DISTINCT city")
	        	.append("FROM address ")
	        	.append(";");
            System.out.println("Request \"" + reqStr.toString());
        	
            /** Creating requests manager */
            pStatement = AddOnSQL
            	.initPreparedStatement(
        			connection,
            		reqStr.toString(),
            		false
            	);
            
            /** Executing SELECT */
            resultSet = pStatement.executeQuery();
            System.out.println("\" done.");
     
            /** Retrieving data from result set */
            while(resultSet.next()) {
            	cities.add(resultSet.getString("city"));
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
        return cities;
	}

	@Override
	public List<String> listCountry() {
		//TODO To test
		List<String> countries = new ArrayList<>();
		Connection connection = daoFactory.getConnection();
        PreparedStatement pStatement = null;
        ResultSet resultSet = null;
        try {
        	StringBuilder reqStr = new StringBuilder()
    			.append("SELECT DISTINCT country")
	        	.append("FROM address ")
	        	.append(";");
            System.out.println("Request \"" + reqStr.toString());
            
            /** Creating requests manager */
            pStatement = AddOnSQL
            	.initPreparedStatement(
        			connection,
            		reqStr.toString(),
            		false
            	);
            
            /** Executing SELECT */
            resultSet = pStatement.executeQuery();
            System.out.println("\" done.");
     
            /** Retrieving data from result set */
            while(resultSet.next()) {
            	countries.add(resultSet.getString("country"));
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
        return countries;
	}

	@Override
	public List<String> listPostal() {
		//TODO To test
		List<String> postals = new ArrayList<>();
		Connection connection = daoFactory.getConnection();
        PreparedStatement pStatement = null;
        ResultSet resultSet = null;
        try {
        	StringBuilder reqStr = new StringBuilder()
    			.append("SELECT DISTINCT postal")
	        	.append("FROM address ")
	        	.append(";");
            System.out.println("Request \"" + reqStr.toString());
            
            /** Creating requests manager */
            pStatement = AddOnSQL
            	.initPreparedStatement(
        			connection,
            		reqStr.toString(),
            		false
            	);
            
            /** Executing SELECT */
            resultSet = pStatement.executeQuery();
            System.out.println("\" done.");
     
            /** Retrieving data from result set */
            while(resultSet.next()) {
            	postals.add(resultSet.getString("postal"));
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
        return postals;
	}

	@Override
	public List<String> listStreet() {
		//TODO To test
		List<String> streets = new ArrayList<>();
		Connection connection = daoFactory.getConnection();
        PreparedStatement pStatement = null;
        ResultSet resultSet = null;
        try {
        	StringBuilder reqStr = new StringBuilder()
    			.append("SELECT DISTINCT street")
	        	.append("FROM address ")
	        	.append(";");
            System.out.println("Request \"" + reqStr.toString());
            
            /** Creating requests manager */
            pStatement = AddOnSQL
            	.initPreparedStatement(
        			connection,
            		reqStr.toString(),
            		false
            	);
            
            /** Executing SELECT */
            resultSet = pStatement.executeQuery();
            System.out.println("\" done.");
     
            /** Retrieving data from result set */
            while(resultSet.next()) {
            	streets.add(resultSet.getString("street"));
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
        return streets;
	}

}
