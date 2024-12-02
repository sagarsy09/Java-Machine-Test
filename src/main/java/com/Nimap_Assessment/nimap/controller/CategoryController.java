package com.Nimap_Assessment.nimap.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Nimap_Assessment.nimap.dao.CategoryDao;
import com.Nimap_Assessment.nimap.dto.ResponseStructure;
import com.Nimap_Assessment.nimap.entity.Category;
import com.Nimap_Assessment.nimap.exception.ResourceNotFoundException;
import com.Nimap_Assessment.nimap.service.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired 
	private CategoryDao dao;
	
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Category>>> getAllCategories( 
			@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
		
		return categoryService.getAllCategory(page , size);
	}
	
	
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Category>> createCategories(@RequestBody Category category) {
		return categoryService.createCategory(category);
	}
	
	
		
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Category>> getById(@PathVariable int id){
		return categoryService.findById(id);
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<ResponseStructure<Category>> updateCategory(@PathVariable int id , @RequestBody Category updatedCategory){
		
		Category category =dao.getCategoryById(id);
		
		if(category == null) { 
			 throw new ResourceNotFoundException("Category not found");
		}
		
		category.setName(updatedCategory.getName());
		category.setDescription(updatedCategory.getDescription());
		category.setProducts(updatedCategory.getProducts());
		
		return categoryService.updateCategory(category);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<Category>> deleteById(@PathVariable int id){
		return categoryService.deleteById(id);
	}
}
