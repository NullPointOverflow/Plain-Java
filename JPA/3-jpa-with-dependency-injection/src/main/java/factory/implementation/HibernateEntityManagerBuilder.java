package factory.implementation;

import org.hibernate.jpa.HibernatePersistenceProvider;

import factory.interfacial.EntityManagerBuilder;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.FlushModeType;
import jakarta.persistence.spi.PersistenceUnitInfo;

@ApplicationScoped
public class HibernateEntityManagerBuilder implements EntityManagerBuilder {

	private static EntityManagerFactory hibernateFactory = null;
	private static PersistenceUnitInfo persistenceUnitInfo = null;

	@Inject
	private void getPersistenceUnitInfo(PersistenceUnitInfo newPersistenceUnitInfo) {

		persistenceUnitInfo = newPersistenceUnitInfo;

	}

	@Produces
	@Override
	public EntityManager getEntityManager() {

		if (hibernateFactory == null) {

			hibernateFactory = new HibernatePersistenceProvider()
					.createContainerEntityManagerFactory(persistenceUnitInfo, persistenceUnitInfo.getProperties());

		}

		EntityManager connection = hibernateFactory.createEntityManager();

		connection.setFlushMode(FlushModeType.COMMIT);

		return connection;

	}

	public void closeEntityManager(@Disposes EntityManager connection) {

		connection.close();

	}

}
