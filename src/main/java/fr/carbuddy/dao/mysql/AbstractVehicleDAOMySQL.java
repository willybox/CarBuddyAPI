package fr.carbuddy.dao.mysql;

import static fr.carbuddy.global.ConstantValues.BRAND;
import static fr.carbuddy.global.ConstantValues.MODEL;
import static fr.carbuddy.global.ConstantValues.TOTAL_PLACES;

import java.sql.ResultSet;
import java.sql.SQLException;

import fr.carbuddy.bean.Vehicle;
import fr.carbuddy.dao.DAOFactory;
import fr.carbuddy.dao.VehicleDAO;

public abstract class AbstractVehicleDAOMySQL implements VehicleDAO {
	
	protected DAOFactory daoFactory;
	
	public AbstractVehicleDAOMySQL(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	
	/**
	 * Utility function allowing to match columns from Vehicle
	 * table and bean properties
	 * @param resultSet
	 * @return
	 * @throws SQLException
	 */
	protected Vehicle getVehicleFromResultSet(
		ResultSet resultSet
	) throws SQLException {
		
		Vehicle vehicle = new Vehicle();
		vehicle.setId(resultSet.getLong("id"));
        
		/** -- DriverProfile properties -- */
		vehicle.setBrand(resultSet.getString(BRAND));
		vehicle.setModel(resultSet.getString(MODEL));
		vehicle.setTotalPlaces(resultSet.getInt(TOTAL_PLACES));
		
	    return vehicle;
	}
}
