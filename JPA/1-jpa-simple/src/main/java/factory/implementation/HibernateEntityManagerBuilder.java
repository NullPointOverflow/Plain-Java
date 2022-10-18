package factory.implementation;

import factory.interfacial.EntityManagerBuilder;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.FlushModeType;
import jakarta.persistence.Persistence;

public class HibernateEntityManagerBuilder implements EntityManagerBuilder {

	private static final EntityManagerFactory HIBERNATE_FACTORY = Persistence
			.createEntityManagerFactory("hibernate-h2");

	@Override
	public EntityManager getEntityManager() {

		EntityManager connection = HIBERNATE_FACTORY.createEntityManager();

		connection.setFlushMode(FlushModeType.COMMIT);

		return connection;

	}

}
