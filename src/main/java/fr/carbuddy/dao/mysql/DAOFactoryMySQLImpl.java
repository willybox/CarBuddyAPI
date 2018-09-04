package fr.carbuddy.dao.mysql;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

import fr.carbuddy.dao.AddressDAO;
import fr.carbuddy.dao.BuddyProfileDAO;
import fr.carbuddy.dao.DAOFactory;
import fr.carbuddy.dao.DriverProfileDAO;
import fr.carbuddy.dao.UserDAO;
import fr.carbuddy.dao.VehicleDAO;
import fr.carbuddy.dao.mysql.impl.AddressDAOMySQLImpl;
import fr.carbuddy.dao.mysql.impl.BuddyProfileDAOMySQLImpl;
import fr.carbuddy.dao.mysql.impl.DriverProfileDAOMySQLImpl;
import fr.carbuddy.dao.mysql.impl.UserDAOMySQLImpl;
import fr.carbuddy.dao.mysql.impl.VehicleDAOMySQLImpl;
import fr.carbuddy.exception.DAOConfigurationRuntimeException;
import fr.carbuddy.global.GlobalValues;

public class DAOFactoryMySQLImpl implements DAOFactory {

    private static final String FICHIER_PROPERTIES = "/fr/carbuddy/dao.properties";
    private static final String PROPERTY_URL = "url";
    private static final String PROPERTY_DRIVER = "driver";
    private static final String PROPERTY_USER = "user";
    private static final String PROPERTY_PASSWORD = "pass";
	private BoneCP connectionPool;

	private DAOFactoryMySQLImpl(BoneCP connectionPool) {
        this.connectionPool = connectionPool;
    }

	/**
	 * Will retrieve database connection information, load JDBC driver and
	 * make factory instance
	 * @return
	 * @throws DAOConfigurationRuntimeException
	 */
    public static DAOFactory getInstance() throws DAOConfigurationRuntimeException {
        Properties properties = new Properties();
        String url;
        String userName;
        String pass;
        String driver;

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream fichierProperties = classLoader.getResourceAsStream(FICHIER_PROPERTIES);

        /** If properties file not in compiled JAR */
        if(fichierProperties == null) {
        	File fileSystemProperties = new File("src/main/java/" + FICHIER_PROPERTIES);
        	if(!GlobalValues.NO_DEBUG) {
        		System.out.println(fileSystemProperties.getAbsolutePath());
        	}
        	if(fileSystemProperties.exists()) {
        		try {
					fichierProperties = new FileInputStream(fileSystemProperties);
				} catch (FileNotFoundException e) {
					throw new DAOConfigurationRuntimeException(
		            	"Le fichier "
			            + fileSystemProperties.getAbsolutePath()
			            + " est inaccessible."
		            );
				}
        	} else {
	            throw new DAOConfigurationRuntimeException(
	            	"Le fichier properties "
		            + FICHIER_PROPERTIES
		            + " est introuvable."
	            );
        	}
        }
        try {
            properties.load(fichierProperties);
            url = properties.getProperty(PROPERTY_URL);
            driver = properties.getProperty(PROPERTY_DRIVER);
            userName = properties.getProperty(PROPERTY_USER);
            pass = properties.getProperty(PROPERTY_PASSWORD);

        } catch (IOException e) {
            throw new DAOConfigurationRuntimeException(
            	"Impossible de charger le fichier properties " + FICHIER_PROPERTIES,
	            e
            );
        }

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new DAOConfigurationRuntimeException("Le driver est introuvable dans le classpath.", e);
        }
        BoneCP connPool = null;
        try {
            /**
             * Creation of connections pool configuration with BoneCPConfig
             * and associated setters.
             */
            BoneCPConfig config = new BoneCPConfig();
            
            /** Basic connection setting */
            config.setJdbcUrl(url);
            config.setUsername(userName);
            config.setPassword(pass);
            
            /** Pool size setting (Greatly depending on traffic!)*/
            config.setMinConnectionsPerPartition(5);
            config.setMaxConnectionsPerPartition(10);
            config.setPartitionCount(2);
            
            /** Creating pool with BoneCPConfig */
            connPool = new BoneCP(config);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOConfigurationRuntimeException(
            	"Erreur de configuration du pool de connexions.",
            	e
            );
        }
        
        /** Saving pool created into instance variable */
        DAOFactory instance = new DAOFactoryMySQLImpl(connPool);
        return instance;
    }

    /**
     * <p>Getting a Connection with com.jolbox.bonecp.BoneCP
     * that can handle pool connection (Better multiple
     * connections manager)</p>
     * <p><i><u>Note:</u> When a connections pool is set, calling
     * Connection::close will not literally close the
     * connection but will return it to the pool.
     * </i></p>
     */
	public Connection getConnection() {
		try {
			return connectionPool.getConnection();
		} catch (SQLException e) {
			throw new DAOConfigurationRuntimeException(
				"Cannot get pool connection " + e.getMessage()
			);
		}
	}

	@Override
    public UserDAO getUserDAO() {
        return new UserDAOMySQLImpl(this);
    }
	
	@Override
    public AddressDAO getAddressDAO() {
        return new AddressDAOMySQLImpl(this);
    }

	@Override
	public BuddyProfileDAO getBuddyProfileDAO() {
		return new BuddyProfileDAOMySQLImpl(this);
	}

	@Override
	public DriverProfileDAO getDriverProfileDAO() {
		return new DriverProfileDAOMySQLImpl(this);
	}

	@Override
	public VehicleDAO getVehicleDAO() {
		return new VehicleDAOMySQLImpl(this);
	}

}
