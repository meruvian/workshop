package org.meruvian.workshop.jaxrs.service;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.meruvian.workshop.jaxrs.entity.Category;
import org.meruvian.workshop.jaxrs.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class RestCategoryService implements CategoryService {
	@Inject
	private CategoryRepository categoryRepository;
	
	@Override
	public Category getCategoryById(long id) {
		return categoryRepository.findById(id);
	}

	@Override
	public List<Category> findCategoryByName(String name) {
		return categoryRepository.findByName(name);
	}

	@Override
	@Transactional
	public Category saveCategory(Category category) {
		category.setId(0);
		
		return categoryRepository.save(category);
	}

	@Override
	@Transactional
	public Category updateCategory(long id, Category category) {
		Category ori = getCategoryById(category.getId());
		if (ori != null) {
			ori.setName(category.getName());
			ori.setSubCategory(category.getSubCategory());
		}
		
		return ori;
	}

	@Override
	@Transactional
	public void deleteCategory(long id) {
		categoryRepository.delete(id);
	}

}
