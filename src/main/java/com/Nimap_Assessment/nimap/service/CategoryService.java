package com.Nimap_Assessment.nimap.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Nimap_Assessment.nimap.dao.CategoryDao;
import com.Nimap_Assessment.nimap.dto.ResponseStructure;
import com.Nimap_Assessment.nimap.entity.Category;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryDao dao;
	
	
	public ResponseEntity<ResponseStructure<List<Category>>> getAllCategory(int pageNumber , int pageSize) {
		
		Pageable p =  PageRequest.of(pageNumber, pageSize);	
		
		Page<Category> categoryPages = dao.fetchAllCategory(p);	
		List<Category> content = categoryPages.getContent();
		
		ResponseStructure<List<Category>> structure = new ResponseStructure<>();		
		structure.setStatusCode(HttpStatus.OK.value());
		structure.setMessage("Resouce Retrieved Successfully!");
		structure.setData(content);
		
		return new ResponseEntity<ResponseStructure<List<Category>>>(structure , HttpStatus.OK);
		
	}


	public ResponseEntity<ResponseStructure<Category>> createCategory(Category category) {
		
		Category returnedCategory = dao.createCategory(category);
		
		ResponseStructure<Category> structure = new ResponseStructure<Category>();
		structure.setStatusCode(HttpStatus.CREATED.value());
		structure.setMessage("Created Succefully");
		structure.setData(returnedCategory);
		
		return new ResponseEntity<ResponseStructure<Category>>(structure , HttpStatus.CREATED);
	}
	
	
	public ResponseEntity<ResponseStructure<Category>> findById(int id){
		Category categoryById = dao.getCategoryById(id);
		
		ResponseStructure<Category> structure = new ResponseStructure<Category>();
		structure.setStatusCode(HttpStatus.OK.value());
		structure.setMessage("Resouce Retrieved Successfully!");
		structure.setData(categoryById);
		
		return new ResponseEntity<ResponseStructure<Category>>(structure , HttpStatus.OK);
	}
	
	
	
	public ResponseEntity<ResponseStructure<Category>> updateCategory(Category updatedCategory) {

		dao.updateCategory(updatedCategory);
		
		ResponseStructure<Category> structure = new ResponseStructure<Category>();
		
		structure.setStatusCode(HttpStatus.ACCEPTED.value());
		structure.setMessage("Category Updated Succesfully!");
		structure.setData(updatedCategory);
		
		return new ResponseEntity<ResponseStructure<Category>>(structure , HttpStatus.ACCEPTED);
	}
	
	
	public ResponseEntity<ResponseStructure<Category>> deleteById(int id){
		dao.deleteCategory(id);
		
		ResponseStructure<Category> structure = new ResponseStructure<Category>();
		structure.setStatusCode(HttpStatus.OK.value());
		structure.setMessage("Resource deleted");
		structure.setData(null);
		
		return new ResponseEntity<ResponseStructure<Category>>(structure , HttpStatus.OK);
	}
	
}
