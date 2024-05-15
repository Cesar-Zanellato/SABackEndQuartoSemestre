package com.back.fortesupermercados.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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

import com.back.fortesupermercados.dtos.products.ProdutoEntrada;
import com.back.fortesupermercados.dtos.products.ProdutoSaida;
import com.back.fortesupermercados.entities.Produto;
import com.back.fortesupermercados.services.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @GetMapping
    public ResponseEntity<List<ProdutoSaida>> list(Pageable page, Produto exemplo){
        List<ProdutoSaida> list = service.list(page, exemplo);
        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<ProdutoSaida> create(@RequestBody ProdutoEntrada produto){
        ProdutoSaida saida = service.create(produto);
        return new ResponseEntity(saida, HttpStatus.CREATED);

    }

    @GetMapping("/{codigoInterno}")
    public ResponseEntity<ProdutoSaida> read(@PathVariable Long codigoInterno){
        ProdutoSaida produto = service.read(codigoInterno);
        if(produto == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(produto);
    }

    @PutMapping("/{codigoInterno}")
    public ResponseEntity<ProdutoSaida> update(@PathVariable Long codigoInterno, @RequestBody ProdutoEntrada produto){
        ProdutoSaida saida = service.update(codigoInterno, produto);
        if(saida == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(saida);
    }

    @DeleteMapping("/{codigoInterno}")
    public ResponseEntity delete(@PathVariable Long codigoInterno){
        service.delete(codigoInterno);
        return ResponseEntity.noContent().build();
    }
}
