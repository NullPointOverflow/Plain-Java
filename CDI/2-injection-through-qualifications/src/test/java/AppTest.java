import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import entity.Orchestra;
import implementation.percussion.Bell;
import implementation.percussion.Xylophone;
import implementation.string.Harp;
import implementation.string.Violin;
import implementation.wind.Trumpet;
import implementation.wind.Tuba;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;

@TestMethodOrder(OrderAnnotation.class)
class AppTest {

	private static SeContainer container = null;
	private static Orchestra orchestra = null;

	@BeforeAll
	public static void init() {

		container = SeContainerInitializer.newInstance().initialize();

		orchestra = container.select(Orchestra.class).get();

	}

	@AfterAll
	public static void terminate() {

		container.close();

	}

	@Test
	@Order(1)
	public void the_Orchestra_object_should_not_be_null() {

		Assertions.assertNotNull(orchestra);

	}

	@Test
	@Order(2)
	public void the_Orchestra_object_should_play_the_harp_sound() {

		Assertions.assertEquals(new Harp().sound(), orchestra.playString());

	}

	@Test
	@Order(3)
	public void the_Orchestra_object_should_play_the_bell_sound() {

		Assertions.assertEquals(new Bell().sound(), orchestra.playPercussion());

	}

	@Test
	@Order(4)
	public void the_Orchestra_object_should_play_the_tuba_sound() {

		Assertions.assertEquals(new Tuba().sound(), orchestra.playWind());

	}

	@Test
	@Order(5)
	public void the_Orchestra_object_should_play_the_violin_sound() {

		Assertions.assertEquals(new Violin().sound(), orchestra.playStringSolo());

	}

	@Test
	@Order(6)
	public void the_Orchestra_object_should_play_the_xylophone_sound() {

		Assertions.assertEquals(new Xylophone().sound(), orchestra.playPercussionSolo());

	}

	@Test
	@Order(7)
	public void the_Orchestra_object_should_play_the_trumpet_sound() {

		Assertions.assertEquals(new Trumpet().sound(), orchestra.playWindSolo());

	}

	@Test
	@Order(8)
	public void the_Orchestra_object_should_play_one_instrument_from_each_category() {

		String sounds = orchestra.playAllTogether();

		String[] instruments = sounds.split(", ");

		Assertions.assertEquals(3, instruments.length);

	}

}
