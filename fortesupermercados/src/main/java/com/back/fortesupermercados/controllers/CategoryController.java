package com.back.fortesupermercados.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.back.fortesupermercados.dtos.categories.CategoryInput;
import com.back.fortesupermercados.dtos.categories.CategoryOutput;
import com.back.fortesupermercados.dtos.products.ProductOutput;
import com.back.fortesupermercados.services.CategoryService;

@RestController
@CrossOrigin("*")
@RequestMapping("/categories")
public class CategoryController {
     
    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryOutput> create(@RequestBody CategoryInput category){
        CategoryOutput output = categoryService.create(category);
        return new ResponseEntity(output, HttpStatus.CREATED);

    }

    @GetMapping("/{categoryId}/subcategories")
    public ResponseEntity<List<CategoryOutput>> getSubcategoriesByCategoryId(@PathVariable Long categoryId) {
        List<CategoryOutput> subcategories = categoryService.getSubcategoriesByCategoryId(categoryId);
        return ResponseEntity.ok(subcategories);
    }

    @GetMapping("/{categoryId}/products")
    public ResponseEntity<List<ProductOutput>> getProductsByCategoryId(@PathVariable Long categoryId) {
        List<ProductOutput> products = categoryService.getProductsByCategoryId(categoryId);
        return ResponseEntity.ok(products);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryOutput> update(@PathVariable Long id, @RequestBody CategoryInput category){
        CategoryOutput output = categoryService.update(id, category);
        return ResponseEntity.ok(output);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
