package com.back.fortesupermercados.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.back.fortesupermercados.entities.Pedido;
import com.back.fortesupermercados.repositories.PedidoRepository;

@RestController
@RequestMapping("/pedido")
public class PedidoController {
    
    @Autowired
    private PedidoRepository repository;

    @GetMapping
    public List<Pedido> list(){
        return repository.findAll();
    }

    @PostMapping
    public Pedido create(@RequestBody Pedido pedido){
        return repository.save(pedido);
    }

    @GetMapping("/{id}")
    public Pedido read(@PathVariable Long id){
        return repository.findById(id).get();
    }

    @PutMapping
    public Pedido update(@RequestBody Pedido pedido){
        return repository.save(pedido);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        repository.deleteById(id);
    }
}
