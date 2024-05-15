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

import com.back.fortesupermercados.dtos.subcategories.SubcategoriaEntrada;
import com.back.fortesupermercados.dtos.subcategories.SubcategoriaSaida;
import com.back.fortesupermercados.services.SubcategoriaService;

@RestController
@RequestMapping("/subcategorias")
public class SubcategoriaController {
     
    @Autowired
    private SubcategoriaService service;

    @GetMapping
    public ResponseEntity<List<SubcategoriaSaida>> list(){
        List<SubcategoriaSaida> list = service.list();
        return ResponseEntity.ok(list);
    }
    
    @PostMapping
    public ResponseEntity<SubcategoriaSaida> create(@RequestBody SubcategoriaEntrada subcategoria){
        SubcategoriaSaida saida = service.create(subcategoria);
        return new ResponseEntity(saida, HttpStatus.CREATED);

    }

    @GetMapping("/{id}")
    public ResponseEntity<SubcategoriaSaida> read(@PathVariable Long id){
        SubcategoriaSaida subcategoria = service.read(id);
        if(subcategoria == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(subcategoria);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubcategoriaSaida> update(@PathVariable Long id, @RequestBody SubcategoriaEntrada subcategoria){
        SubcategoriaSaida saida = service.update(id, subcategoria);
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
