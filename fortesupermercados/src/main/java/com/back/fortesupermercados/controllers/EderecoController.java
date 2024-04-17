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

import com.back.fortesupermercados.entities.Endereco;
import com.back.fortesupermercados.repositories.EnderecoRepository;
@RestController
@RequestMapping("/endereco")
public class EderecoController {
    
    @Autowired
    private EnderecoRepository repository;

    @GetMapping
    public List<Endereco> list(){
        return repository.findAll();
    }

    @PostMapping
    public Endereco create(@RequestBody Endereco endereco){
        return repository.save(endereco);
    }

    @GetMapping("/{id}")
    public Endereco read(@PathVariable Long id){
        return repository.findById(id).get();
    }

    @PutMapping
    public Endereco update(@RequestBody Endereco endereco){
        return repository.save(endereco);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        repository.deleteById(id);
    }
}
