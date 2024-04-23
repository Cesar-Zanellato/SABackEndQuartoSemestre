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

import com.back.fortesupermercados.dtos.usuarios.UsuarioEntrada;
import com.back.fortesupermercados.dtos.usuarios.UsuarioSaida;
import com.back.fortesupermercados.services.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping
    public ResponseEntity<List<UsuarioSaida>> list(){
        List<UsuarioSaida> list = service.list();
        return ResponseEntity.ok(list);
    }
    
    @PostMapping
    public ResponseEntity<UsuarioSaida> create(@RequestBody UsuarioEntrada usuario){
        UsuarioSaida saida = service.create(usuario);
        return new ResponseEntity(saida, HttpStatus.CREATED);

    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioSaida> read(@PathVariable Long id){
        UsuarioSaida usuario = service.read(id);
        if(usuario == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioSaida> update(@PathVariable Long id, @RequestBody UsuarioEntrada usuario){
        UsuarioSaida saida = service.update(id, usuario);
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