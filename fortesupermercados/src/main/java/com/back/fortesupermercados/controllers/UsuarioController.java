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

import com.back.fortesupermercados.entities.Usuario;
import com.back.fortesupermercados.repositories.UsuarioRepository;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    
    @Autowired
    private UsuarioRepository repository;

    @GetMapping
    public List<Usuario> list(){
        return repository.findAll();
    }

    @PostMapping
    public Usuario create(@RequestBody Usuario usuario){
        return repository.save(usuario);
    }

    @GetMapping("/{id}")
    public Usuario read(@PathVariable Long id){
        return repository.findById(id).get();
    }

    @PutMapping
    public Usuario update(@RequestBody Usuario usuario){
        return repository.save(usuario);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        repository.deleteById(id);
    }
}