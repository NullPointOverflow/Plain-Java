import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import implementation.Car;
import interfacial.Vehicle;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;

@TestMethodOrder(OrderAnnotation.class)
class AppTest {

	private static SeContainer container = null;
	private static Vehicle vehicle = null;

	@BeforeAll
	public static void init() {

		container = SeContainerInitializer.newInstance().initialize();

		vehicle = container.select(Vehicle.class).get();

	}

	@AfterAll
	public static void terminate() {

		container.close();

	}

	@Test
	@Order(1)
	public void the_Vehicle_object_should_not_be_null() {

		Assertions.assertNotNull(vehicle);

	}

	@Test
	@Order(2)
	public void the_Vehicle_object_should_have_the_movement_of_a_car() {

		Assertions.assertEquals(new Car().move(), vehicle.move());

	}

}
