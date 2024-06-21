package com.back.fortesupermercados.repositories;

import com.back.fortesupermercados.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByCategoryId(Long categoryId);

    List<Product> findBySubcategoryId(Long subcategoryId);

}
