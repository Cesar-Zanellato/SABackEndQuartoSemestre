package com.back.fortesupermercados.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.back.fortesupermercados.dtos.deliveries.DeliveryInput;
import com.back.fortesupermercados.dtos.deliveries.DeliveryOutput;
import com.back.fortesupermercados.services.DeliveryService;

@RestController @Validated
@RequestMapping("/delivery")
public class DeliveryController {
    
    @Autowired
    private DeliveryService service;

    @GetMapping
    public ResponseEntity<List<DeliveryOutput>> list(){
        List<DeliveryOutput> list = service.list();
        return ResponseEntity.ok(list);
    }
    
    @PostMapping
    public ResponseEntity<DeliveryOutput> create(@RequestBody DeliveryInput pedido){
        DeliveryOutput saida = service.create(pedido);
        return new ResponseEntity(saida, HttpStatus.CREATED);

    }

    @GetMapping("/{id}")
    public ResponseEntity<DeliveryOutput> read(@PathVariable Long id){
        DeliveryOutput pedido = service.read(id);
        if(pedido == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pedido);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DeliveryOutput> update(@PathVariable Long id, @RequestBody DeliveryInput pedido){
        DeliveryOutput saida = service.update(id, pedido);
        if(saida == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(saida);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
