package entity.consumer.interfacial;

import java.util.function.Consumer;

import entity.supplier.News;

public interface Media extends Consumer<News> {

	public String getPublishedNews();

}
