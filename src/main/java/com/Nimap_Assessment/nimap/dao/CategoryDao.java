package com.Nimap_Assessment.nimap.dao;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.Nimap_Assessment.nimap.entity.Category;
import com.Nimap_Assessment.nimap.entity.Product;
import com.Nimap_Assessment.nimap.repository.CategoryRepository;

@Repository
public class CategoryDao {
	
	@Autowired
	private CategoryRepository repository;
	
	public Page<Category> fetchAllCategory(Pageable pageable){
		return repository.findAll(pageable);
	}
	

	public Category createCategory(Category category) {
		List<Product> products = category.getProducts();
		
		if(products != null) {
			for(Product product : products) {
				product.setCategory(category);
			}
		}
		return repository.save(category);		
	}
	

	public Category getCategoryById(int id) {
		
		 Optional<Category> optional = repository.findById(id);
		 
		 if(optional.isPresent()) {
			 return optional.get();
		 }
		 return null;
	}
	
	
	public Category updateCategory(Category category) {
		return repository.save(category);
	}
	
	public void deleteCategory(int id) {
		repository.deleteById(id);
	}
}
