package factory.implementation;

import java.util.Properties;

import factory.interfacial.PersistenceUnitInfoFactory;
import jakarta.persistence.spi.PersistenceUnitInfo;
import persistence.abstraction.AbstractPersistenceUnitInfo;
import persistence.implementation.HibernatePersistenceUnitInfo;
import util.PropertiesFileReader;

public class HibernatePersistenceUnitInfoFactory implements PersistenceUnitInfoFactory {

	private static final PropertiesFileReader READER = new PropertiesFileReader();
	private static AbstractPersistenceUnitInfo persistenceUnitInfo = null;

	@Override
	public PersistenceUnitInfo getPersistenceUnit() {

		if (persistenceUnitInfo == null) {

			Properties properties = READER.readPropertiesFile("/properties/jpa.properties")
					.readPropertiesFile("/properties/hibernate.properties").getReadProperties();

			persistenceUnitInfo = new HibernatePersistenceUnitInfo(properties)
					.setNonJtaDataSource(new HikariDataSourceFactory().getDataSource());

		}

		return persistenceUnitInfo;

	}

}
