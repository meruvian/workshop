package org.meruvian.workshop.jaxrs.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.meruvian.workshop.jaxrs.entity.News;

public class NewsRepository {
	private long sequence = 0;
	private List<News> news = new ArrayList<News>();
	
	public News getById(long id) {
		for (News n : news) {
			if (n.getId() == id) {
				return n;
			}
		}
		
		return null;
	}
	
	public List<News> findByTitle(String title) {
		List<News> results = new ArrayList<News>();
		
		for (News n : news) {
			if (n.getTitle().contains(title)) {
				results.add(n);
			}
		}
		
		return results;
	}
	
	public News save(News n) {
		n.setId(sequence++);
		n.setCreateDate(new Date());
		news.add(n);

		return n;
	}
	
	public News update(News n) {
		News ori = getById(n.getId());
		if (ori != null) {
			ori.setTitle(n.getTitle());
			ori.setContent(n.getContent());
		}
		
		return ori;
	}
	
	public void delete(long id) {
		News n = getById(id);
		news.remove(n);
	}
}
