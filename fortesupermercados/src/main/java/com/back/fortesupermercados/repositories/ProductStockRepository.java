package com.back.fortesupermercados.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.back.fortesupermercados.entities.ProductStock;

public interface ProductStockRepository extends JpaRepository<ProductStock, Long>{
    
    @Query("SELECT CASE WHEN ps.quantity > 0 THEN true ELSE false END FROM ProductStock ps WHERE ps.product.id = :productId")
    boolean isProductInStock(@Param("productId") Long productId);
    
    @Query("SELECT ps.quantity FROM ProductStock ps WHERE ps.product.id = :productId")
    int getProductStockQuantity(@Param("productId") Long productId);
}
