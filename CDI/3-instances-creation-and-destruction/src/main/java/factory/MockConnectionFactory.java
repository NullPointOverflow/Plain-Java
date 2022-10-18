package factory;

import implementation.Connection;
import interfacial.MockConnection;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;

@ApplicationScoped
public class MockConnectionFactory {

	@Produces
	public MockConnection createConnection() {

		return new Connection();

	}

	public void destroyConnection(@Disposes MockConnection connection) {

		connection.close();

	}

}
