package factory.implementation;

import org.hibernate.jpa.HibernatePersistenceProvider;

import factory.interfacial.EntityManagerBuilder;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.FlushModeType;
import jakarta.persistence.spi.PersistenceUnitInfo;

public class HibernateEntityManagerBuilder implements EntityManagerBuilder {

	private static EntityManagerFactory hibernateFactory = null;

	@Override
	public EntityManager getEntityManager() {

		if (hibernateFactory == null) {

			PersistenceUnitInfo persistenceUnitInfo = new HibernatePersistenceUnitInfoFactory().getPersistenceUnit();

			hibernateFactory = new HibernatePersistenceProvider()
					.createContainerEntityManagerFactory(persistenceUnitInfo, persistenceUnitInfo.getProperties());

		}

		EntityManager connection = hibernateFactory.createEntityManager();

		connection.setFlushMode(FlushModeType.COMMIT);

		return connection;

	}

}
