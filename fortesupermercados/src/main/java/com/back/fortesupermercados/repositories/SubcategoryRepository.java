package com.back.fortesupermercados.repositories;

import com.back.fortesupermercados.entities.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubcategoryRepository extends JpaRepository<Subcategory, Long> {

    List<Subcategory> findByCategoryId(Long categoryId);

}
