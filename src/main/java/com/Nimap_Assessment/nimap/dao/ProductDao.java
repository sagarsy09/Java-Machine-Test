package com.Nimap_Assessment.nimap.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.Nimap_Assessment.nimap.entity.Product;
import com.Nimap_Assessment.nimap.repository.ProductRepository;

@Repository
public class ProductDao {
	@Autowired
	private ProductRepository repository;
	
	public Page<Product> fetchAllProducts(Pageable pageable){
		return repository.findAll(pageable);
	}
	
	public Product createProduct(Product product) {
		return repository.save(product);
	}
	
	
	public Product getProductById(int id) {
		
		 Optional<Product> optional = repository.findById(id);
		 
		 if(optional.isPresent()) {
			 return optional.get();
		 }
		 return null;
	}
	
	public Product updateProduct(Product product) {
		return repository.save(product);
	}
	
	public String deleteProduct(int id) {
		Product product = getProductById(id);
		if(product != null) {
			repository.deleteById(id);
			return "Deleted";
		}
		return "Not Deleted";
	}
}
