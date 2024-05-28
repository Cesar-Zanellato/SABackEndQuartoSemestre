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

import com.back.fortesupermercados.dtos.shopping.ShoppingInput;
import com.back.fortesupermercados.dtos.shopping.ShoppingOutput;
import com.back.fortesupermercados.services.ShoppingService;

@RestController @Validated
@CrossOrigin("*")
@RequestMapping("/shopping")
public class ShoppingController {

    @Autowired
    private ShoppingService service;

    @GetMapping
    public ResponseEntity<List<ShoppingOutput>> list(){
        List<ShoppingOutput> list = service.list();
        return ResponseEntity.ok(list);
    }
    
    @PostMapping
    public ResponseEntity<ShoppingOutput> create(@RequestBody ShoppingInput shopping){
        ShoppingOutput output = service.create(shopping);
        return new ResponseEntity(output, HttpStatus.CREATED);

    }

    @GetMapping("/{id}")
    public ResponseEntity<ShoppingOutput> read(@PathVariable Long id){
        ShoppingOutput shopping = service.read(id);
        if(shopping == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(shopping);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShoppingOutput> update(@PathVariable Long id, @RequestBody ShoppingInput shopping){
        ShoppingOutput output = service.update(id, shopping);
        if(output == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(output);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
