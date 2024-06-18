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

import com.back.fortesupermercados.dtos.address.AddressInput;
import com.back.fortesupermercados.dtos.address.AddressOutput;
import com.back.fortesupermercados.services.AddressService;

@RestController @Validated
@CrossOrigin("*")
@RequestMapping("/address")
public class AddressController {
    
    @Autowired
    private AddressService service;

    @GetMapping
    public ResponseEntity<List<AddressOutput>> list(){
        List<AddressOutput> list = service.list();
        return ResponseEntity.ok(list);
    }
    
    @PostMapping
    public ResponseEntity<AddressOutput> create(@RequestBody AddressInput address){
        AddressOutput output = service.create(address);
        return new ResponseEntity(output, HttpStatus.CREATED);

    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressOutput> read(@PathVariable Long id){
        AddressOutput address = service.read(id);
        return ResponseEntity.ok(address);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressOutput> update(@PathVariable Long id, @RequestBody AddressInput address){
        AddressOutput output = service.update(id, address);
        return ResponseEntity.ok(output);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
