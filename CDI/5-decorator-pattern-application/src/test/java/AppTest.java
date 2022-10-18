import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import interfacial.Worker;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;

class AppTest {

	private static SeContainer container = null;
	private static Worker worker = null;

	@BeforeAll
	public static void init() {

		container = SeContainerInitializer.newInstance().initialize();

		worker = container.select(Worker.class).get();

	}

	@AfterAll
	public static void terminate() {

		container.close();

	}

	@Test
	public void the_general_resume_should_have_both_manager_and_programmer_resumes() {

		String dailyResume = worker.work("DO THE DAILY WORK.");

		Assertions.assertTrue(dailyResume.contains("DAILY REPORT: Manager"));

		Assertions.assertTrue(dailyResume.contains("DAILY REPORT: Programmer"));

	}

}
