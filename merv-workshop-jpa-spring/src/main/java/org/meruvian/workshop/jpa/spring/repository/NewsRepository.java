package org.meruvian.workshop.jpa.spring.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.meruvian.workshop.jpa.spring.entity.News;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public class NewsRepository {
	@PersistenceContext
	private EntityManager entityManager;
	
	public News findById(long id) {
		return entityManager.find(News.class, id);
	}
	
	@Transactional
	public void save(News news) {
		if (news.getId() == 0) {
			entityManager.persist(news);
		} else {
			News n = findById(news.getId());
			n.setTitle(news.getTitle());
			n.setContent(news.getContent());
		}
	}
	
	public List<News> findByTitle(String title) {
		String q = "SELECT n FROM News n WHERE n.title LIKE ?1";
		TypedQuery<News> query = entityManager.createQuery(q, News.class);
		query.setParameter(1, title);
		
		return query.getResultList();
	}
}
