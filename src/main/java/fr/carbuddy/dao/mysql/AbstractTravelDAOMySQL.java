package fr.carbuddy.dao.mysql;

import static fr.carbuddy.global.ConstantValues.BUDDY;
import static fr.carbuddy.global.ConstantValues.DATE;
import static fr.carbuddy.global.ConstantValues.DRIVER;
import static fr.carbuddy.global.ConstantValues.RENDEZ_VOUS;
import static fr.carbuddy.global.ConstantValues.TERMINUS;
import static fr.carbuddy.global.ConstantValues.TRAVEL_STATUS;

import java.sql.ResultSet;
import java.sql.SQLException;

import fr.carbuddy.bean.Travel;
import fr.carbuddy.dao.DAOFactory;
import fr.carbuddy.dao.TravelDAO;
import fr.carbuddy.enumeration.TravelStatus;

public abstract class AbstractTravelDAOMySQL implements TravelDAO {
	
	protected DAOFactory daoFactory;
	
	public AbstractTravelDAOMySQL(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	
	/**
	 * Utility function allowing to match columns from Travel
	 * table and bean properties
	 * @param resultSet
	 * @return
	 * @throws SQLException
	 */
	protected Travel getTravelFromResultSet(
		ResultSet resultSet
	) throws SQLException {
		
		Travel travel = new Travel();
		travel.setId(resultSet.getLong("id"));
        
		/** -- DriverProfile properties -- */
		
		travel.setBuddy(daoFactory.getUserDAO().findById(resultSet.getLong(BUDDY)));
		travel.setDriver(daoFactory.getUserDAO().findById(resultSet.getLong(DRIVER)));
		travel.setRendezVous(daoFactory.getAddressDAO().findById(resultSet.getLong(RENDEZ_VOUS)));
		travel.setTerminus(daoFactory.getAddressDAO().findById(resultSet.getLong(TERMINUS)));
		travel.setTravelStatus(TravelStatus.getStatus(resultSet.getInt(TRAVEL_STATUS)));
		travel.setDate(resultSet.getDate(DATE));
		
	    return travel;
	}
}
