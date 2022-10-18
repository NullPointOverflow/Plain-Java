package persistence.abstraction;

import java.net.URL;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import entity.Product;
import jakarta.persistence.spi.ClassTransformer;
import jakarta.persistence.spi.PersistenceUnitInfo;
import jakarta.persistence.spi.PersistenceUnitTransactionType;
import util.Defaults;

public abstract class AbstractPersistenceUnitInfo implements PersistenceUnitInfo {

	private static final String JPA_VERSION = "3.0";
	private final String persistenceUnitName;
	private final Properties properties;
	private final List<String> managedClassNames = List.of(Product.class.getName());
	private PersistenceUnitTransactionType transactionType = PersistenceUnitTransactionType.RESOURCE_LOCAL;
	private DataSource nonJtaDataSource = null;
	private DataSource jtaDataSource = null;

	public AbstractPersistenceUnitInfo(String persistenceUnitName, Properties properties) {

		this.persistenceUnitName = persistenceUnitName;

		this.properties = properties;

	}

	@Override
	public String getPersistenceUnitName() {

		return this.persistenceUnitName;

	}

	@Override
	public PersistenceUnitTransactionType getTransactionType() {

		return this.transactionType;

	}

	@Override
	public DataSource getJtaDataSource() {

		return this.jtaDataSource;

	}

	public AbstractPersistenceUnitInfo setJtaDataSource(DataSource jtaDataSource) {

		this.jtaDataSource = jtaDataSource;

		this.nonJtaDataSource = null;

		this.transactionType = PersistenceUnitTransactionType.JTA;

		return this;

	}

	@Override
	public DataSource getNonJtaDataSource() {

		return this.nonJtaDataSource;

	}

	public AbstractPersistenceUnitInfo setNonJtaDataSource(DataSource nonJtaDataSource) {

		this.nonJtaDataSource = nonJtaDataSource;

		this.jtaDataSource = null;

		this.transactionType = PersistenceUnitTransactionType.RESOURCE_LOCAL;

		return this;

	}

	@Override
	public List<String> getMappingFileNames() {

		return Defaults.getEmptyList();

	}

	@Override
	public List<URL> getJarFileUrls() {

		return Defaults.getEmptyList();

	}

	@Override
	public URL getPersistenceUnitRootUrl() {

		return null;

	}

	@Override
	public List<String> getManagedClassNames() {

		return this.managedClassNames;

	}

	@Override
	public boolean excludeUnlistedClasses() {

		return Boolean.TRUE;

	}

	@Override
	public Properties getProperties() {

		return this.properties;

	}

	@Override
	public String getPersistenceXMLSchemaVersion() {

		return JPA_VERSION;

	}

	@Override
	public ClassLoader getClassLoader() {

		return Thread.currentThread().getContextClassLoader();

	}

	@Override
	public void addTransformer(ClassTransformer transformer) {
	}

	@Override
	public ClassLoader getNewTempClassLoader() {

		return Thread.currentThread().getContextClassLoader();

	}

}
