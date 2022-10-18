package factory.implementation;

import java.util.Properties;

import javax.sql.DataSource;

import factory.interfacial.PersistenceUnitInfoFactory;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import jakarta.persistence.spi.PersistenceUnitInfo;
import persistence.abstraction.AbstractPersistenceUnitInfo;
import persistence.implementation.HibernatePersistenceUnitInfo;
import util.PropertiesFileReader;

@ApplicationScoped
public class HibernatePersistenceUnitInfoFactory implements PersistenceUnitInfoFactory {

	private static final PropertiesFileReader READER = new PropertiesFileReader();
	private static AbstractPersistenceUnitInfo persistenceUnitInfo = null;
	private static DataSource dataSource;

	@Inject
	private void getDataSource(DataSource newDataSource) {

		dataSource = newDataSource;

	}

	@Produces
	@Override
	public PersistenceUnitInfo getPersistenceUnit() {

		if (persistenceUnitInfo == null) {

			Properties properties = READER.readPropertiesFile("/properties/jpa.properties")
					.readPropertiesFile("/properties/hibernate.properties").getReadProperties();

			persistenceUnitInfo = new HibernatePersistenceUnitInfo(properties).setNonJtaDataSource(dataSource);

		}

		return persistenceUnitInfo;

	}

}
