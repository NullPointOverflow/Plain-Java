import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import entity.Journalist;
import entity.consumer.implementation.Magazine;
import entity.consumer.implementation.NewsPaper;
import entity.consumer.implementation.SocialMedia;
import entity.consumer.implementation.SportNewsPaper;
import entity.consumer.interfacial.Media;
import entity.supplier.News;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;

@TestMethodOrder(OrderAnnotation.class)
class AppTest {

	private static SeContainer container = null;
	private static Journalist journalist = null;
	private static Media magazine = null;
	private static Media newsPaper = null;
	private static Media socialMedia = null;
	private static Media sportNewsPaper = null;

	@BeforeAll
	public static void init() {

		container = SeContainerInitializer.newInstance().initialize();

		journalist = container.select(Journalist.class).get();

		magazine = container.select(Magazine.class).get();

		newsPaper = container.select(NewsPaper.class).get();

		socialMedia = container.select(SocialMedia.class).get();

		sportNewsPaper = container.select(SportNewsPaper.class).get();

	}

	@AfterAll
	public static void terminate() {

		container.close();

	}

	@Test
	@Order(1)
	public void only_the_genera_media_should_get_the_general_news() {

		String news = "WEATHER FORECAST...";

		journalist.sendNews(News.of(news));

		Assertions.assertTrue(magazine.getPublishedNews().contains(news));

		Assertions.assertTrue(newsPaper.getPublishedNews().contains(news));

		Assertions.assertTrue(socialMedia.getPublishedNews().contains(news));

		Assertions.assertNull(sportNewsPaper.getPublishedNews());

	}

	@Test
	@Order(2)
	public void only_the_specific_media_should_get_the_specific_news() {

		String specificNews = "WORLD CUP NEWS...";

		journalist.sendSpecificNews(News.of(specificNews));

		Assertions.assertFalse(magazine.getPublishedNews().contains(specificNews));

		Assertions.assertFalse(newsPaper.getPublishedNews().contains(specificNews));

		Assertions.assertFalse(socialMedia.getPublishedNews().contains(specificNews));

		Assertions.assertTrue(sportNewsPaper.getPublishedNews().contains(specificNews));

	}

}
