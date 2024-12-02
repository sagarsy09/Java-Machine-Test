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

import com.Nimap_Assessment.nimap.dao.ProductDao;
import com.Nimap_Assessment.nimap.dto.ResponseStructure;
import com.Nimap_Assessment.nimap.entity.Category;
import com.Nimap_Assessment.nimap.entity.Product;
import com.Nimap_Assessment.nimap.entity.ProductWithCategory;
import com.Nimap_Assessment.nimap.exception.ResourceNotFoundException;
import com.Nimap_Assessment.nimap.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProductDao dao;
	
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Product>>> getAllProduct( 
			@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
		
		return productService.getAllProduct(page , size);
	}
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Product>> creatProduct(@RequestBody Product product){
		return productService.createProduct(product);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<ProductWithCategory>> getById(@PathVariable int id){
		return productService.findById(id);
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<ResponseStructure<Product>> updateProduct(@PathVariable int id , @RequestBody Product updatedProduct){
		
		Product product =dao.getProductById(id);
		
		if(product == null) { 
			 throw new ResourceNotFoundException("Product not found");
		}
		
		product.setName(updatedProduct.getName());
		product.setPrice(updatedProduct.getPrice());
		product.setCategory(updatedProduct.getCategory());
		
		return productService.updateProduct(product);
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteById(@PathVariable int id){
		return productService.deleteById(id);
	}
}
