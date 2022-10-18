import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import interfacial.MockConnection;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;

@TestMethodOrder(OrderAnnotation.class)
class AppTest {

	private static SeContainer container = null;
	private static MockConnection connection = null;
	private static ByteArrayOutputStream outputStream = null;

	@BeforeAll
	public static void init() {

		container = SeContainerInitializer.newInstance().initialize();

		connection = container.select(MockConnection.class).get();

		outputStream = new ByteArrayOutputStream();

		System.setOut(new PrintStream(outputStream));

	}

	@Test
	@Order(1)
	public void the_Connection_object_should_not_be_null() {

		Assertions.assertNotNull(connection);

	}

	@Test
	@Order(2)
	public void the_Connection_object_should_be_close_after_close_the_cdi_container() {

		container.close();

		Assertions.assertTrue(outputStream.toString().contains("CONNECTION CLOSED."));

	}

}
