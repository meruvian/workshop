package org.meruvian.workshop.jpa.spring.repository;

import java.util.List;

import org.meruvian.workshop.jpa.spring.entity.News;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends CrudRepository<News, Long> {
	News findById(long id);
	
	List<News> findByTitleContains(String title);
}
