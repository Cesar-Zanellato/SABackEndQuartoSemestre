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
import com.back.fortesupermercados.services.CategoryService;

@RestController
@CrossOrigin("*")
@RequestMapping("/category")
public class CategoryController {
     
    @Autowired
    private CategoryService service;

    @GetMapping
    public ResponseEntity<List<CategoryOutput>> list(){
        List<CategoryOutput> list = service.list();
        return ResponseEntity.ok(list);
    }
    
    @PostMapping
    public ResponseEntity<CategoryOutput> create(@RequestBody CategoryInput category){
        CategoryOutput output = service.create(category);
        return new ResponseEntity(output, HttpStatus.CREATED);

    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryOutput> read(@PathVariable Long id){
        CategoryOutput category = service.read(id);
        return ResponseEntity.ok(category);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryOutput> update(@PathVariable Long id, @RequestBody CategoryInput category){
        CategoryOutput output = service.update(id, category);
        return ResponseEntity.ok(output);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
