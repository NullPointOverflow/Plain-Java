package persistence.implementation;

import java.util.Properties;

import org.hibernate.jpa.HibernatePersistenceProvider;

import jakarta.persistence.SharedCacheMode;
import jakarta.persistence.ValidationMode;
import persistence.abstraction.AbstractPersistenceUnitInfo;

public class HibernatePersistenceUnitInfo extends AbstractPersistenceUnitInfo {

	public HibernatePersistenceUnitInfo(Properties properties) {

		super("hibernate-persistence-unit", properties);

	}

	@Override
	public String getPersistenceProviderClassName() {

		return HibernatePersistenceProvider.class.getName();

	}

	@Override
	public SharedCacheMode getSharedCacheMode() {

		return SharedCacheMode.ENABLE_SELECTIVE;

	}

	@Override
	public ValidationMode getValidationMode() {

		return ValidationMode.AUTO;

	}

}
