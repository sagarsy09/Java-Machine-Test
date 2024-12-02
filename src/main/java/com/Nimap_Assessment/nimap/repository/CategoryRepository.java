package com.Nimap_Assessment.nimap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Nimap_Assessment.nimap.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{

}
