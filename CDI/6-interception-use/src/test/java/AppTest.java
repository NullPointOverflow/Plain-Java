import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.regex.Pattern;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import supplier.FastSupplier;
import supplier.SlowSupplier;

@TestMethodOrder(OrderAnnotation.class)
class AppTest {

	private static SeContainer container = null;
	private static FastSupplier fastSupplier = null;
	private static SlowSupplier slowSupplier = null;

	@BeforeAll
	public static void init() {

		container = SeContainerInitializer.newInstance().initialize();

		fastSupplier = container.select(FastSupplier.class).get();

		slowSupplier = container.select(SlowSupplier.class).get();

	}

	@AfterAll
	public static void terminate() {

		container.close();

	}

	@Test
	@Order(1)
	public void the_get_method_from_FastSupplier_class_should_generate_a_text_log() {

		List<String> readedFile = null;

		fastSupplier.get();

		Assertions.assertTrue(new File("log.txt").exists());

		try {

			readedFile = Files.readAllLines(Path.of("log.txt"));

		} catch (IOException e) {

			Assertions.fail();

		}

		Assertions.assertTrue(readedFile.get(0).equals("Audit result:"));

		Assertions.assertTrue(readedFile.get(1).contains("class supplier.FastSupplier"));

		Assertions.assertTrue(readedFile.get(2).contains("supplier.FastSupplier.get()"));

		Assertions.assertTrue(Pattern.compile("\\d").matcher(readedFile.get(3)).find());

	}

	@Test
	@Order(2)
	public void the_get_method_from_SlowSupplier_class_should_generate_a_text_log() {

		List<String> readedFile = null;

		slowSupplier.get();

		Assertions.assertTrue(new File("log.txt").exists());

		try {

			readedFile = Files.readAllLines(Path.of("log.txt"));

		} catch (IOException e) {

			Assertions.fail();

		}

		Assertions.assertTrue(readedFile.get(0).equals("Audit result:"));

		Assertions.assertTrue(readedFile.get(1).contains("class supplier.SlowSupplier"));

		Assertions.assertTrue(readedFile.get(2).contains("supplier.SlowSupplier.get()"));

		Assertions.assertTrue(Pattern.compile("\\d").matcher(readedFile.get(3)).find());

	}

}
