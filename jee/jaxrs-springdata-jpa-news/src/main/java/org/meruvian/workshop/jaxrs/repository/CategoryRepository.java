package org.meruvian.workshop.jaxrs.repository;

import java.util.List;

import org.meruvian.workshop.jaxrs.entity.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
	Category findById(long id);
	
	@Query("SELECT lg FROM Category lg WHERE lg.name LIKE %?1%")
	List<Category> findByName(String title);
}
