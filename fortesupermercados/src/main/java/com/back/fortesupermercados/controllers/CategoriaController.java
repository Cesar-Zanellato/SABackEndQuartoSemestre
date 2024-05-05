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

import com.back.fortesupermercados.dtos.categoria.CategoriaEntrada;
import com.back.fortesupermercados.dtos.categoria.CategoriaSaida;
import com.back.fortesupermercados.services.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
     
    @Autowired
    private CategoriaService service;

    @GetMapping
    public ResponseEntity<List<CategoriaSaida>> list(){
        List<CategoriaSaida> list = service.list();
        return ResponseEntity.ok(list);
    }
    
    @PostMapping
    public ResponseEntity<CategoriaSaida> create(@RequestBody CategoriaEntrada categoria){
        CategoriaSaida saida = service.create(categoria);
        return new ResponseEntity(saida, HttpStatus.CREATED);

    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaSaida> read(@PathVariable Long id){
        CategoriaSaida categoria = service.read(id);
        if(categoria == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(categoria);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaSaida> update(@PathVariable Long id, @RequestBody CategoriaEntrada categoria){
        CategoriaSaida saida = service.update(id, categoria);
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
