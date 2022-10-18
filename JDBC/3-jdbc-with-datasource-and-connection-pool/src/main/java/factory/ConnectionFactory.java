package factory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import util.PropertiesFileReader;

public abstract class ConnectionFactory {

	private static final PropertiesFileReader READER = new PropertiesFileReader();
	private static DataSource dataSource = null;

	public static Connection getConnection() {

		Connection conexao = null;

		try {

			if (dataSource == null) {

				Properties basicProperties = READER.readPropertiesFile("/properties/connection.properties")
						.getReadProperties();

				HikariConfig configuration = new HikariConfig(
						READER.getPropertiesFromFile("/properties/hikaricp.properties"));

				configuration.setJdbcUrl(basicProperties.getProperty("url"));
				configuration.setUsername(basicProperties.getProperty("user"));
				configuration.setPassword(basicProperties.getProperty("password"));

				HikariDataSource hikariDataSource = new HikariDataSource(configuration);

				dataSource = hikariDataSource;

			}

			conexao = dataSource.getConnection();

			conexao.setAutoCommit(false);

		} catch (SQLException e) {

			throw new RuntimeException("Could not establish a connection with the database.", e);

		}

		return conexao;

	}

}
