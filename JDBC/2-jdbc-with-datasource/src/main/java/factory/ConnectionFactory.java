package factory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.h2.jdbcx.JdbcDataSource;

import util.PropertiesFileReader;

public abstract class ConnectionFactory {

	private static final PropertiesFileReader READER = new PropertiesFileReader();
	private static DataSource dataSource = null;

	public static Connection getConnection() {

		Connection connection = null;

		try {

			if (dataSource == null) {

				Properties propriedades = READER.getPropertiesFromFile("/properties/database.properties");

				JdbcDataSource h2DataSource = new JdbcDataSource();

				h2DataSource.setURL(propriedades.getProperty("url"));
				h2DataSource.setUser(propriedades.getProperty("user"));
				h2DataSource.setPassword(propriedades.getProperty("password"));

				dataSource = h2DataSource;

			}

			connection = dataSource.getConnection();

			connection.setAutoCommit(false);

		} catch (SQLException e) {

			throw new RuntimeException("Could not establish a connection with the database.", e);

		}

		return connection;

	}

}
