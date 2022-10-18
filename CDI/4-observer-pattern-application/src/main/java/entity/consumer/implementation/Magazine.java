package entity.consumer.implementation;

import annotation.GeneralNews;
import entity.consumer.interfacial.Media;
import entity.supplier.News;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;

@ApplicationScoped
public class Magazine implements Media {

	private String publishedNews;

	@Override
	public String getPublishedNews() {

		return this.publishedNews;

	}

	@Override
	public void accept(@Observes @GeneralNews News news) {

		this.publishedNews = "NEWS PUBLISHED IN MAGAZINES: " + news.get();

	}

}
