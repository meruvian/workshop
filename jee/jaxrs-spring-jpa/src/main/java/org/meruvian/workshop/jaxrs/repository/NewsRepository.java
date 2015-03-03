package org.meruvian.workshop.jaxrs.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.meruvian.workshop.jaxrs.entity.News;
import org.springframework.stereotype.Repository;

@Repository
public class NewsRepository {
	@PersistenceContext
	private EntityManager entityManager;
	
	public News getById(long id) {
		return entityManager.find(News.class, id);
	}
	
	public List<News> findByTitle(String title) {
		String ql = "SELECT n FROM News n WHERE n.title LIKE ?1";
		TypedQuery<News> query = entityManager.createQuery(ql, News.class);
		query.setParameter(1, "%" + title + "%");
		
		return query.getResultList();
	}
	
	public News save(News n) {
		entityManager.persist(n);
		
		return n;
	}
	
	public void delete(long id) {
		News n = getById(id);
		entityManager.remove(n);
	}
}
