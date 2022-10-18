package entity;

import annotation.GeneralNews;
import annotation.SpecficNews;
import entity.supplier.News;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Event;
import jakarta.inject.Inject;

@ApplicationScoped
public class Journalist {

	@Inject
	@GeneralNews
	private Event<News> event;

	@Inject
	@SpecficNews
	private Event<News> specificEvent;

	public void sendNews(News news) {

		this.event.fire(news);

	}

	public void sendSpecificNews(News news) {

		this.specificEvent.fire(news);

	}

}
