package com.Nimap_Assessment.nimap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Nimap_Assessment.nimap.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

}
