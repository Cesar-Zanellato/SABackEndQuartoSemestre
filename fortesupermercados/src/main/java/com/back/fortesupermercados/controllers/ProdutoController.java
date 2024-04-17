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

import com.back.fortesupermercados.entities.Produto;
import com.back.fortesupermercados.repositories.ProdutoRepository;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoRepository repository;

    @GetMapping
    public List<Produto> list(){
        return repository.findAll();
    }

    @PostMapping
    public Produto create(@RequestBody Produto produto){
        return repository.save(produto);
    }

    @GetMapping("/{codigoInterno}")
    public Produto read(@PathVariable Long codigoInterno){
        return repository.findById(codigoInterno).get();
    }

    @PutMapping
    public Produto update(@RequestBody Produto produto){
        return repository.save(produto);
    }

    @DeleteMapping("/{codigoInterno}")
    public void delete(@PathVariable Long codigoInterno){
        repository.deleteById(codigoInterno);
    }
}
