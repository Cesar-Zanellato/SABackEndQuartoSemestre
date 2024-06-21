package com.back.fortesupermercados.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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

import com.back.fortesupermercados.dtos.products.ProductInput;
import com.back.fortesupermercados.dtos.products.ProductOutput;
import com.back.fortesupermercados.entities.Product;
import com.back.fortesupermercados.services.ProductService;

@RestController
@Validated
@CrossOrigin("*")
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ProductOutput> create(@RequestBody ProductInput product) {
        ProductOutput output = productService.create(product);
        return new ResponseEntity(output, HttpStatus.CREATED);

    }

    @GetMapping
    public ResponseEntity<List<ProductOutput>> list(Pageable page, Product example) {
        List<ProductOutput> list = productService.list(page, example);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/by-subcategory/{subcategoryId}")
    public ResponseEntity<List<ProductOutput>> getProductsBySubcategoryId(@PathVariable Long subcategoryId) {
        List<ProductOutput> products = productService.getProductsBySubcategoryId(subcategoryId);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductOutput> read(@PathVariable Long id) {
        ProductOutput product = productService.read(id);
        return ResponseEntity.ok(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductOutput> update(@PathVariable Long id, @RequestBody ProductInput product) {
        ProductOutput output = productService.update(id, product);
        return ResponseEntity.ok(output);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
