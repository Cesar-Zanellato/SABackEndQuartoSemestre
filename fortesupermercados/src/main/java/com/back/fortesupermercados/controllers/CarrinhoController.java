package com.back.fortesupermercados.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.back.fortesupermercados.dtos.carrinhos.CarrinhoSaida;
import com.back.fortesupermercados.dtos.carrinhos.CarrinhoEntrada;
import com.back.fortesupermercados.services.CarrinhoService;

@RestController
@RequestMapping("/carrinho")
public class CarrinhoController {

    @Autowired
    private CarrinhoService service;

    @GetMapping
    public ResponseEntity<List<CarrinhoSaida>> list(){
        List<CarrinhoSaida> list = service.list();
        return ResponseEntity.ok(list);
    }
    
    @PostMapping
    public ResponseEntity<CarrinhoSaida> create(@RequestBody CarrinhoEntrada carrinho){
        CarrinhoSaida saida = service.create(carrinho);
        return new ResponseEntity(saida, HttpStatus.CREATED);

    }

    @GetMapping("/{id}")
    public ResponseEntity<CarrinhoSaida> read(@PathVariable Long id){
        CarrinhoSaida carrinho = service.read(id);
        if(carrinho == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(carrinho);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarrinhoSaida> update(@PathVariable Long id, @RequestBody CarrinhoEntrada carrinho){
        CarrinhoSaida saida = service.update(id, carrinho);
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
