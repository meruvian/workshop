package org.meruvian.workshop.jaxrs.service;

import java.util.List;

import org.meruvian.workshop.jaxrs.entity.News;
import org.meruvian.workshop.jaxrs.repository.NewsRepository;

public class RestNewsService implements NewsService {
	private NewsRepository newsRepository = NewsRepository.getInstance();
	
	@Override
	public News getNewsById(long id) {
		return newsRepository.getById(id);
	}

	@Override
	public List<News> findNewsByTitle(String title) {
		return newsRepository.findByTitle(title);
	}

	@Override
	public News saveNews(News news) {
		return newsRepository.save(news);
	}

	@Override
	public News updateNews(long id, News news) {
		news.setId(id);
		
		return newsRepository.update(news);
	}

	@Override
	public void deleteNews(long id) {
		newsRepository.delete(id);
	}

}
