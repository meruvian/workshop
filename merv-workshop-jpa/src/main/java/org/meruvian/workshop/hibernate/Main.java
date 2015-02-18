package org.meruvian.workshop.hibernate;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.meruvian.workshop.hibernate.entity.News;
import org.meruvian.workshop.hibernate.entity.Category;


public class Main {
	private EntityManagerFactory emf;
	
	public static void main(String[] args) {
		Main main = new Main();
		main.initEntityManagerFactory();
		
		main.saveNews();
		main.saveNews();
		main.saveNews();
		
		main.saveCategory();
		
		main.getAllNews();
		
		main.setCategoryToNews();
		
		main.closeEntityManagerFactory();
	}
	
	// initialize persistence unit
	private void initEntityManagerFactory() {
		this.emf = Persistence.createEntityManagerFactory("meruvian-workshop-jpa");
	}
	
	private void closeEntityManagerFactory() {
		this.emf.close();
	}
	
	private void saveNews() {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		// Begin transaction
		tx.begin();
		
		News news = new News();
		news.setTitle("Breaking news!");
		news.setContent("Hello, this is the content of the news");
		news.setCreateDate(new Date());
		
		// Save News
		em.persist(news);
		
		// Commit Transaction
		tx.commit();
		em.close();
	}
	
	private void saveCategory() {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		tx.begin();
		
		Category category = new Category();
		category.setTitle("Sport");
		category.setSubtitle("All about sports");
		
		em.persist(category);
		
		tx.commit();
		em.close();
	}
	
	private void getAllNews() {
		EntityManager em = emf.createEntityManager();
		
		TypedQuery<News> query = em.createQuery("SELECT n FROM News n WHERE n.title LIKE ?1", News.class);
		query.setParameter(1, "%news%");
		
		List<News> news = query.getResultList();
		for (News n : news) {
			System.out.println("ID      : " + n.getId());
			System.out.println("TITLE   : " + n.getTitle());
			System.out.println("CONTENT : " + n.getContent());
			System.out.println();
		}
		
		em.close();
	}
	
	private void setCategoryToNews() {
		EntityManager em = emf.createEntityManager();
		
		TypedQuery<News> query = em.createQuery("SELECT n FROM News n", News.class);
		List<News> news = query.getResultList();
		
		Category category = em.find(Category.class, 1L);
		
		EntityTransaction tx = em.getTransaction();
		
		tx.begin();
		for (News n : news) {
			n.setCategory(category);
		}
		tx.commit();
		
		em.close();
	}
}
