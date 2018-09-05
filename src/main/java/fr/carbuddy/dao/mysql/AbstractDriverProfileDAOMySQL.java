package fr.carbuddy.dao.mysql;

import static fr.carbuddy.global.ConstantValues.DESTINATION;
import static fr.carbuddy.global.ConstantValues.MAX_BUDDIES;
import static fr.carbuddy.global.ConstantValues.NUMBER_LUGGAGES;
import static fr.carbuddy.global.ConstantValues.PREFERED_START;
import static fr.carbuddy.global.ConstantValues.TRAVEL_DATE;
import static fr.carbuddy.global.ConstantValues.VEHICLE_ID;

import java.sql.ResultSet;
import java.sql.SQLException;

import fr.carbuddy.bean.Address;
import fr.carbuddy.bean.DriverProfile;
import fr.carbuddy.bean.Vehicle;
import fr.carbuddy.dao.DAOFactory;
import fr.carbuddy.dao.DriverProfileDAO;

public abstract class AbstractDriverProfileDAOMySQL implements DriverProfileDAO {
	
	protected DAOFactory daoFactory;
	
	public AbstractDriverProfileDAOMySQL(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	
	/**
	 * Utility function allowing to match columns from DriverProfile
	 * table and bean properties
	 * @param resultSet
	 * @return
	 * @throws SQLException
	 */
	protected DriverProfile getDriverProfileFromResultSet(
		ResultSet resultSet
	) throws SQLException {
		
		DriverProfile driverProfile = new DriverProfile();
		driverProfile.setId(resultSet.getLong("id"));
        
		/** -- DriverProfile properties -- */
		driverProfile.setTravelDate(resultSet.getDate(TRAVEL_DATE));
		driverProfile.setMaxBuddies(resultSet.getInt(MAX_BUDDIES));
		driverProfile.setNumberLuggages(resultSet.getInt(NUMBER_LUGGAGES));
		Long addressDestinationId = resultSet.getLong(DESTINATION);
		Address destination = daoFactory.getAddressDAO().findById(addressDestinationId);
		driverProfile.setDestination(destination);

		Long addressStartId = resultSet.getLong(PREFERED_START);		
		Address preferedStart = daoFactory.getAddressDAO().findById(addressStartId);
		driverProfile.setPreferedStart(preferedStart);

		Long vehicleId = resultSet.getLong(VEHICLE_ID);
		Vehicle vehicle = daoFactory.getVehicleDAO().get(vehicleId);
		driverProfile.setVehicle(vehicle);
		
	    return driverProfile;
	}
}
