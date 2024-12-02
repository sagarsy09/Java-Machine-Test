package com.Nimap_Assessment.nimap.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.Nimap_Assessment.nimap.dao.ProductDao;
import com.Nimap_Assessment.nimap.dto.ResponseStructure;
import com.Nimap_Assessment.nimap.entity.Category;
import com.Nimap_Assessment.nimap.entity.Product;
import com.Nimap_Assessment.nimap.entity.ProductWithCategory;

@Service
public class ProductService {
	
	@Autowired
	private ProductDao dao;
	
	
	public ResponseEntity<ResponseStructure<List<Product>>> getAllProduct(int pageNumber , int pageSize) {
		
		Pageable p =  PageRequest.of(pageNumber, pageSize);	
		
		Page<Product> productPages = dao.fetchAllProducts(p);	
		List<Product> content = productPages.getContent();
		
		ResponseStructure<List<Product>> structure = new ResponseStructure<>();		
		structure.setStatusCode(HttpStatus.OK.value());
		structure.setMessage("Resouce Retrieved Successfully!");
		structure.setData(content);
		
		return new ResponseEntity<ResponseStructure<List<Product>>>(structure , HttpStatus.OK);		
	}
	
	
	public ResponseEntity<ResponseStructure<Product>> createProduct(Product product) {
		
		Product returnedProduct = dao.createProduct(product);
		
//		dao.getProductById();
		
		ResponseStructure<Product> structure = new ResponseStructure<Product>();
		structure.setStatusCode(HttpStatus.CREATED.value());
		structure.setMessage("Created Succefully");
		structure.setData(returnedProduct);
		
		return new ResponseEntity<ResponseStructure<Product>>(structure , HttpStatus.CREATED);
	} 
	
	
	public ResponseEntity<ResponseStructure<ProductWithCategory>> findById(int id){
		
		Product productById = dao.getProductById(id);
		Category category = productById.getCategory();
		
		ProductWithCategory productWithCategory = new ProductWithCategory();
		
		if(category != null && productById != null) {
			productWithCategory.setId(category.getId());
			productWithCategory.setName(category.getName());
			productWithCategory.setDescription(category.getDescription());
			productWithCategory.setProduct(productById);
			
			ResponseStructure<ProductWithCategory> structure = new ResponseStructure<ProductWithCategory>();
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Resouce Retrieved Successfully!");
			structure.setData(productWithCategory);
			
			return new ResponseEntity<ResponseStructure<ProductWithCategory>>(structure , HttpStatus.OK);
		}
		return null;
		
//		ResponseStructure<Product> structure = new ResponseStructure<Product>();
//		structure.setStatusCode(HttpStatus.OK.value());
//		structure.setMessage("Resouce Retrieved Successfully!");
//		structure.setData(productById);
//		
//		return new ResponseEntity<ResponseStructure<Product>>(structure , HttpStatus.OK);
	}
	
	
	public ResponseEntity<ResponseStructure<Product>> updateProduct(Product updatedProduct) {

		dao.updateProduct(updatedProduct);
		
		ResponseStructure<Product> structure = new ResponseStructure<Product>();
		
		structure.setStatusCode(HttpStatus.ACCEPTED.value());
		structure.setMessage("Product Updated Succesfully!");
		structure.setData(updatedProduct);
		
		return new ResponseEntity<ResponseStructure<Product>>(structure , HttpStatus.ACCEPTED);
	}
	
	public ResponseEntity<ResponseStructure<String>> deleteById(int id){
		String msg = dao.deleteProduct(id);
		
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setStatusCode(HttpStatus.OK.value());
		structure.setMessage("Resource deleted");
		structure.setData(msg);
		
		return new ResponseEntity<ResponseStructure<String>>(structure , HttpStatus.OK);
	}

}
