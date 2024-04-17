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

import com.back.fortesupermercados.entities.Carrinho;
import com.back.fortesupermercados.repositories.CarrinhoRepository;

@RestController
@RequestMapping("/carrinho")
public class CarrinhoController {
    @Autowired
    private CarrinhoRepository repository;

    @GetMapping
    public List<Carrinho> list(){
        return repository.findAll();
    }

    @PostMapping
    public Carrinho create(@RequestBody Carrinho carrinho){
        return repository.save(carrinho);
    }

    @GetMapping("/{id}")
    public Carrinho read(@PathVariable Long id){
        return repository.findById(id).get();
    }

    @PutMapping
    public Carrinho update(@RequestBody Carrinho carrinho){
        return repository.save(carrinho);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        repository.deleteById(id);
    }
}
