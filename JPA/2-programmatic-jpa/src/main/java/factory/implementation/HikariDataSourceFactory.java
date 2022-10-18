package factory.implementation;

import java.util.Properties;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import factory.interfacial.DataSourceFactory;
import util.PropertiesFileReader;

public class HikariDataSourceFactory implements DataSourceFactory {

	private static final PropertiesFileReader READER = new PropertiesFileReader();
	private static DataSource dataSource = null;

	@Override
	public DataSource getDataSource() {

		if (dataSource == null) {

			Properties basicProperties = READER.getPropertiesFromFile("/properties/connection.properties");

			HikariConfig configuration = new HikariConfig(
					READER.getPropertiesFromFile("/properties/hikaricp.properties"));

			configuration.setJdbcUrl(basicProperties.getProperty("url"));
			configuration.setUsername(basicProperties.getProperty("user"));
			configuration.setPassword(basicProperties.getProperty("password"));

			HikariDataSource hikariDataSource = new HikariDataSource(configuration);

			dataSource = hikariDataSource;

		}

		return dataSource;

	}

}
