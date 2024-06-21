package com.back.fortesupermercados.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.back.fortesupermercados.dtos.products.ProductOutput;
import com.back.fortesupermercados.dtos.subcategories.SubcategoryInput;
import com.back.fortesupermercados.dtos.subcategories.SubcategoryOutput;
import com.back.fortesupermercados.services.SubcategoryService;

@RestController
@Validated
@CrossOrigin("*")
@RequestMapping("/subcategories")
public class SubcategoryController {

    @Autowired
    private SubcategoryService subcategoryService;

    @GetMapping
    public ResponseEntity<List<SubcategoryOutput>> list() {
        List<SubcategoryOutput> list = subcategoryService.list();
        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<SubcategoryOutput> create(@RequestBody SubcategoryInput subcategory) {
        SubcategoryOutput output = subcategoryService.create(subcategory);
        return new ResponseEntity(output, HttpStatus.CREATED);

    }

    @GetMapping("/{subcategoryId}/products")
    public ResponseEntity<List<ProductOutput>> getProductsBySubcategoryId(@PathVariable Long subcategoryId) {
        List<ProductOutput> products = subcategoryService.getProductsBySubcategoryId(subcategoryId);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubcategoryOutput> read(@PathVariable Long id) {
        SubcategoryOutput subcategory = subcategoryService.read(id);
        return ResponseEntity.ok(subcategory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubcategoryOutput> update(@PathVariable Long id, @RequestBody SubcategoryInput subcategory) {
        SubcategoryOutput output = subcategoryService.update(id, subcategory);
        return ResponseEntity.ok(output);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        subcategoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
