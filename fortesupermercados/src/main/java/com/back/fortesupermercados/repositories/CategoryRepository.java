package com.back.fortesupermercados.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.back.fortesupermercados.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findBySubcategory_Id(Long subcategoryId);
}


