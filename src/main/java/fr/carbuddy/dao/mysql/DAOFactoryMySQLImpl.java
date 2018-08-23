package fr.carbuddy.dao.mysql;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

import fr.carbuddy.dao.AddressDAO;
import fr.carbuddy.dao.DAOFactory;
import fr.carbuddy.dao.UserDAO;
import fr.carbuddy.dao.mysql.impl.AddressDAOMySQLImpl;
import fr.carbuddy.dao.mysql.impl.UserDAOMySQLImpl;
import fr.carbuddy.exception.DAOConfigurationRuntimeException;

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

        if (fichierProperties == null) {
            throw new DAOConfigurationRuntimeException("Le fichier properties " + FICHIER_PROPERTIES + " est introuvable.");
        }
        try {
            properties.load(fichierProperties);
            url = properties.getProperty(PROPERTY_URL);
            driver = properties.getProperty(PROPERTY_DRIVER);
            userName = properties.getProperty(PROPERTY_USER);
            pass = properties.getProperty(PROPERTY_PASSWORD);

        } catch (IOException e) {
            throw new DAOConfigurationRuntimeException("Impossible de charger le fichier properties " + FICHIER_PROPERTIES, e);
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
            
            /** Pool size setting */
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

}
