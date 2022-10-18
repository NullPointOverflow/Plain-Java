package entity.consumer.implementation;

import annotation.SpecficNews;
import entity.consumer.interfacial.Media;
import entity.supplier.News;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;

@ApplicationScoped
public class SportNewsPaper implements Media {

	private String publishedNews;

	@Override
	public String getPublishedNews() {

		return this.publishedNews;

	}

	@Override
	public void accept(@Observes @SpecficNews News news) {

		this.publishedNews = "NEWS PUBLISHED IN SPORTS NEWSPAPERS: " + news.get();

	}

}
