package factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class ConnectionFactory {

	// public static final String DRIVER = "org.h2.Driver";
	public static final String URL = "jdbc:h2:mem:test";
	public static final String USER = "root";
	public static final String PASSWORD = "root";

	public static Connection getConnection() {

		Connection connection = null;

		try {

			connection = DriverManager.getConnection(URL, USER, PASSWORD);

			connection.setAutoCommit(false);

		} catch (SQLException e) {

			throw new RuntimeException("Could not establish a connection with the database.", e);

		}

		return connection;

	}

}
