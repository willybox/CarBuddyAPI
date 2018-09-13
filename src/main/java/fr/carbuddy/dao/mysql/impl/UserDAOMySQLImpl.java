package fr.carbuddy.dao.mysql.impl;

import static fr.carbuddy.global.ConstantValues.ADDRESS_ID;
import static fr.carbuddy.global.ConstantValues.AVATAR;
import static fr.carbuddy.global.ConstantValues.BIRTHDAY;
import static fr.carbuddy.global.ConstantValues.E_MAIL;
import static fr.carbuddy.global.ConstantValues.FIRSTNAME;
import static fr.carbuddy.global.ConstantValues.GENDER;
import static fr.carbuddy.global.ConstantValues.NAME;
import static fr.carbuddy.global.ConstantValues.PASSWORD;
import static fr.carbuddy.global.ConstantValues.PERSON_ID;
import static fr.carbuddy.global.ConstantValues.STATUS_USER;
import static fr.carbuddy.global.ConstantValues.USERNAME;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.carbuddy.bean.User;
import fr.carbuddy.dao.DAOFactory;
import fr.carbuddy.dao.mysql.AbstractUserDAOMySQL;
import fr.carbuddy.enumeration.order.by.UserOrderBy;
import fr.carbuddy.exception.DAORuntimeException;
import fr.carbuddy.exception.NotValidException;
import fr.carbuddy.global.GlobalValues;
import fr.carbuddy.service.PersonService;
import fr.carbuddy.validation.UserValidation;
import util.library.add.on.sql.AddOnSQL;

public class UserDAOMySQLImpl extends AbstractUserDAOMySQL {
	
	public UserDAOMySQLImpl(DAOFactory daoFactory) {
		super(daoFactory);
	}

	@Override
	public User create(User userToCreate) throws DAORuntimeException, NotValidException {
		/** If exception triggered, will be directly thrown */
		new UserValidation(userToCreate).checkValidity();
		
		Connection connection = daoFactory.getConnection();
        PreparedStatement pStatement = null;
        ResultSet resultSet = null;
        try {
        	
        	StringBuilder reqPersonStr = new StringBuilder()
	        	.append("INSERT INTO person(name, firstname, gender, addressId, email, phone, birthday) ")
	        	.append("VALUES(?, ?, ?, ?, ?, ?, ?)")
	        	.append(";")
        	;
        	/** Creating requests manager */
        	PreparedStatement personPreparedStatement = AddOnSQL
        		.initPreparedStatement(
    				connection,
    				reqPersonStr.toString(),
        			true,
        			userToCreate.getName(),
        			userToCreate.getFirstname(),
        			userToCreate.getGender().getValue(),
        			userToCreate.getAddress().getId(),
        			userToCreate.getEmail(),
        			userToCreate.getPhone(),
        			userToCreate.getBirthday()
        		);
        	int personSuccessCount = personPreparedStatement.executeUpdate();
        	ResultSet personRS = personPreparedStatement.getGeneratedKeys();
        	if(personSuccessCount > 0) {
	        	if(personRS.next()) {
	        		userToCreate.setPersonId(personRS.getLong(1));
		        	StringBuilder reqStr = new StringBuilder()
			        	.append("INSERT INTO user(statusUser, userName, password, personId) ")
			        	.append("VALUES(?, ?, ?, ?)")
			        	.append(";")
		        	;
		        	
		        	System.out.print("Request \"" + reqStr.toString());
		        	
		            /** Creating requests manager */
		        	pStatement = AddOnSQL
		        		.initPreparedStatement(
		    				connection,
		        			reqStr.toString(),
		        			true,
		        			userToCreate.getStatusUser().getValue(),
		        			userToCreate.getUsername(),
		        			userToCreate.getPassword(),
		        			userToCreate.getPersonId()
		        		);
		            
		            /** Executing Insert into query */
		            int successCount = pStatement.executeUpdate();
		            System.out.println("\" done.");
		            
		            if(successCount > 0) {
			            ResultSet userRS = pStatement.getGeneratedKeys();
			            if(userRS.next()) {
			            	userToCreate.setId(userRS.getLong("id"));
			            } else {
			            	throw new DAORuntimeException(
			            		"User creation failed, no auto-generated ID returned."
			            	);
			            }
		            } else {
		            	throw new DAORuntimeException("User creation failed.");
		            }
	        	} else {
	            	throw new DAORuntimeException(
	            		"User creation failed, no auto-generated ID returned for Person."
	            	);
	            }
        	} else {
            	throw new DAORuntimeException(
            		"User creation failed at Person creation."
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

        return userToCreate;
	}

	@Override
	public boolean deleteUser(User userToDelete) throws DAORuntimeException {
		Connection connection = daoFactory.getConnection();
        PreparedStatement pStatement = null;
        ResultSet resultSet = null;
        try {
        	
        	StringBuilder reqPersonStr = new StringBuilder()
	        	.append("DELETE FROM person ")
	        	.append("WHERE id=?")
	        	.append(";")
        	;
        	/** Creating requests manager */
        	PreparedStatement personPreparedStatement = AddOnSQL
        		.initPreparedStatement(
    				connection,
    				reqPersonStr.toString(),
        			true,
        			userToDelete.getPersonId()
        		);
        	int personSuccessCount = personPreparedStatement.executeUpdate();
        	PersonService persService = new PersonService(userToDelete);
        	if(personSuccessCount > 0) {
        		System.out.println(
        			"Successfully removed "
        			+ persService.presentWithFirstnameAndName()
        			+ " from database."
        		);
        	} else {
        		System.out.println("Person with id "
        			+ userToDelete.getPersonId()
        			+ " not found in database. Deletion failed."
        		);
        	}

        	StringBuilder reqStr = new StringBuilder()
    	        	.append("DELETE FROM user ")
    	        	.append("WHERE id=?")
    	        	.append(";")
	        	;
	        	
	        	System.out.print("Request \"" + reqStr.toString());
	        	
	            /** Creating requests manager */
	        	pStatement = AddOnSQL
	        		.initPreparedStatement(
	    				connection,
	        			reqStr.toString(),
	        			true,
	        			userToDelete.getId()
	        		);
	            
	            /** Executing Insert into query */
	            int successCount = pStatement.executeUpdate();
	            System.out.println("\" done.");
	            
	            if(successCount > 0) {
	        		System.out.println(
	        			"Successfully removed "
	        			+ userToDelete.getUsername()
	        			+ " from database."
	        		);
	        	} else {
	        		System.out.println("User with id "
	        			+ userToDelete.getId()
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

	@Override
	public User findByEmail(String email) throws DAORuntimeException {
		//TODO To test
		Connection connection = daoFactory.getConnection();
        User user = null;
        PreparedStatement pStatement = null;
        ResultSet resultSet = null;
        try {
        	
        	StringBuilder reqStr = new StringBuilder()
	        	.append("SELECT * ")
	        	.append("FROM user ")
	        	.append("WHERE email=? ")
	        	.append(";")
        	;
        	
        	System.out.print("Request \"" + reqStr.toString());
        	
            /** Creating requests manager */
        	pStatement = AddOnSQL
        		.initPreparedStatement(
    				connection,
        			reqStr.toString(),
        			false,
        			email
        		);
            
            /** Executing SELECT */
            resultSet = pStatement.executeQuery();
            System.out.println("\" done.");
     
            /** Retrieving data from result set */
            if(resultSet.next()) {
                user = getUserFromResultSet(resultSet);
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

        return user;
	}

	@Override
	public User findById(Long userId) throws DAORuntimeException {
		Connection connection = daoFactory.getConnection();
        User user = null;
        PreparedStatement pStatement = null;
        ResultSet resultSet = null;
        try {
        	
        	StringBuilder reqStr = new StringBuilder()
	        	.append("SELECT * ")
	        	.append("FROM user, person ")
	        	.append("WHERE id=? ")
	        	.append("AND user.personId = person.id")
	        	.append(";")
        	;
        	
        	System.out.print("Request \"" + reqStr.toString());
        	
            /** Creating requests manager */
        	pStatement = AddOnSQL
        		.initPreparedStatement(
    				connection,
        			reqStr.toString(),
        			false,
        			userId
        		);
            
            /** Executing SELECT */
            resultSet = pStatement.executeQuery();
            System.out.println("\" done.");
     
            /** Retrieving data from result set */
            if(resultSet.next()) {
                user = getUserFromResultSet(resultSet);
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

        return user;
	}

	@Override
	public boolean updateUser(User userToUpdate, User newUserInfo) throws DAORuntimeException {
		//TODO To test
		Connection connection = daoFactory.getConnection();
		PreparedStatement pStatement = null;
        ResultSet resultSet = null;
        boolean updated = false;
        try {
        	StringBuilder reqStr = new StringBuilder()
	        	.append("UPDATE user ")
	        	.append("SET")
	        	.append(ADDRESS_ID).append("=?, ")
	        	.append(AVATAR).append("=?, ")
	        	.append(BIRTHDAY).append("=?, ")
	        	.append(E_MAIL).append("=?, ")
	        	.append(FIRSTNAME).append("=?, ")
	        	.append(GENDER).append("=?, ")
	        	.append(NAME).append("=?, ")
	        	.append(PASSWORD).append("=?, ")
	        	.append(PERSON_ID).append("=?, ")
	        	.append(STATUS_USER).append("=?, ")
	        	.append(USERNAME).append("=? ")
	        	.append("WHERE id=?")
	        	.append(";")
        	;
        	/** Creating requests manager */
        	pStatement = AddOnSQL
        		.initPreparedStatement(
    				connection,
    				reqStr.toString(),
        			true,
        			newUserInfo.getAddress().getId(),
        			newUserInfo.getAvatar(),
        			newUserInfo.getBirthday(),
        			newUserInfo.getEmail(),
        			newUserInfo.getFirstname(),
        			newUserInfo.getGender(),
        			newUserInfo.getName(),
        			newUserInfo.getPassword(),
        			newUserInfo.getPersonId(),
        			newUserInfo.getStatusUser().getValue(),
        			newUserInfo.getUsername(),
        			userToUpdate.getId()
        		);
        	int successCount = pStatement.executeUpdate();
        	
        	updated = successCount > 0;
            if(!updated) {
            	throw new DAORuntimeException("Address update failed.");
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
	public User findByUsername(String username) {
		Connection connection = daoFactory.getConnection();
        User user = null;
        PreparedStatement pStatement = null;
        ResultSet resultSet = null;
        try {
        	
        	StringBuilder reqStr = new StringBuilder()
	        	.append("SELECT * ")
	        	.append("FROM user, person ")
	        	.append("WHERE ").append(USERNAME).append("=? ")
	        	.append("AND user.").append(PERSON_ID).append(" = person.id")
	        	.append(";")
        	;
        	
        	System.out.print("Request \"" + reqStr.toString());
        	
            /** Creating requests manager */
        	pStatement = AddOnSQL
        		.initPreparedStatement(
    				connection,
        			reqStr.toString(),
        			false,
        			username
        		);
            
            /** Executing SELECT */
            resultSet = pStatement.executeQuery();
            System.out.println("\" done.");
     
            /** Retrieving data from result set */
            if(resultSet.next()) {
                user = getUserFromResultSet(resultSet);
            	long addressId = resultSet.getLong(ADDRESS_ID);
            	user.setAddress(
        			daoFactory.getAddressDAO().findById(addressId)
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

        return user;
	}

	@Override
	public List<User> listUser(UserOrderBy orderBy, boolean arg1) {
		List<User> users = new ArrayList<>();
        
        Connection connection = daoFactory.getConnection();
        if(connection == null) {
        	return users;
        }
        
        PreparedStatement pStatement = null;
        ResultSet resultSet = null;
        try {
        	if(orderBy == null) {
        		orderBy = UserOrderBy.ID_USER;
        	}
        	StringBuilder reqStr = new StringBuilder()
	        	.append("SELECT * ")
	        	.append("FROM user, person ")
	        	.append("WHERE user.").append(PERSON_ID).append(" = person.id ")
	        	.append("ORDER BY ").append(orderBy.toString())
	        	.append(";")
	        ;
        	System.out.print("Request \"" + reqStr.toString());
        	
            /** Creating requests manager */
            pStatement = connection.prepareStatement(reqStr.toString());

            /** Executing SELECT */
            resultSet = pStatement.executeQuery();
            System.out.println("\" done.");
     
            /** Retrieving data from result set */
            while (resultSet.next()) {
            	User user = getUserFromResultSet(resultSet);
            	long addressId = resultSet.getLong(ADDRESS_ID);
            	user.setAddress(
        			daoFactory.getAddressDAO().findById(addressId)
                );
            	users.add(user);
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

        return users;
	}

	@Override
	public List<User> listDrivers(UserOrderBy orderBy, boolean asc) {

		List<User> users = new ArrayList<>();
        
        Connection connection = daoFactory.getConnection();
        if(connection == null) {
        	return users;
        }
        
        PreparedStatement pStatement = null;
        ResultSet resultSet = null;
        try {
        	if(orderBy == null) {
        		orderBy = UserOrderBy.ID_USER;
        	}
        	StringBuilder reqStr = new StringBuilder()
	        	.append("SELECT * ")
	        	.append("FROM user, person ")
	        	.append("WHERE user.").append(PERSON_ID).append(" = person.id ")
	        	.append("AND driverProfile > 0 ")
	        	.append("ORDER BY ").append(orderBy.toString())
	        	.append(";")
	        ;
        	System.out.print("Request \"" + reqStr.toString());
        	
            /** Creating requests manager */
            pStatement = connection.prepareStatement(reqStr.toString());

            /** Executing SELECT */
            resultSet = pStatement.executeQuery();
            System.out.println("\" done.");
     
            /** Retrieving data from result set */
            while (resultSet.next()) {
            	User user = getUserFromResultSet(resultSet);
            	long addressId = resultSet.getLong(ADDRESS_ID);
            	user.setAddress(
        			daoFactory.getAddressDAO().findById(addressId)
                );
            	users.add(user);
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

        return users;
	}

	@Override
	public List<User> listBuddies(UserOrderBy orderBy, boolean asc) {

		List<User> users = new ArrayList<>();
        
        Connection connection = daoFactory.getConnection();
        if(connection == null) {
        	return users;
        }
        
        PreparedStatement pStatement = null;
        ResultSet resultSet = null;
        try {
        	if(orderBy == null) {
        		orderBy = UserOrderBy.ID_USER;
        	}
        	StringBuilder reqStr = new StringBuilder()
	        	.append("SELECT * ")
	        	.append("FROM user, person ")
	        	.append("WHERE user.").append(PERSON_ID).append(" = person.id ")
	        	.append("AND buddyProfile > 0 ")
	        	.append("ORDER BY ").append(orderBy.toString())
	        	.append(";")
	        ;
        	System.out.print("Request \"" + reqStr.toString());
        	
            /** Creating requests manager */
            pStatement = connection.prepareStatement(reqStr.toString());

            /** Executing SELECT */
            resultSet = pStatement.executeQuery();
            System.out.println("\" done.");
     
            /** Retrieving data from result set */
            while (resultSet.next()) {
            	User user = getUserFromResultSet(resultSet);
            	long addressId = resultSet.getLong(ADDRESS_ID);
            	user.setAddress(
        			daoFactory.getAddressDAO().findById(addressId)
                );
            	users.add(user);
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

        return users;
	}

}
