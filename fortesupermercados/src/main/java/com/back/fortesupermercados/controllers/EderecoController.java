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

import com.back.fortesupermercados.dtos.address.EnderecoEntrada;
import com.back.fortesupermercados.dtos.address.EnderecoSaida;
import com.back.fortesupermercados.services.EnderecoService;
@RestController
@RequestMapping("/endereco")
public class EderecoController {
    
    @Autowired
    private EnderecoService service;

    @GetMapping
    public ResponseEntity<List<EnderecoSaida>> list(){
        List<EnderecoSaida> list = service.list();
        return ResponseEntity.ok(list);
    }
    
    @PostMapping
    public ResponseEntity<EnderecoSaida> create(@RequestBody EnderecoEntrada endereco){
        EnderecoSaida saida = service.create(endereco);
        return new ResponseEntity(saida, HttpStatus.CREATED);

    }

    @GetMapping("/{id}")
    public ResponseEntity<EnderecoSaida> read(@PathVariable Long id){
        EnderecoSaida endereco = service.read(id);
        if(endereco == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(endereco);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnderecoSaida> update(@PathVariable Long id, @RequestBody EnderecoEntrada endereco){
        EnderecoSaida saida = service.update(id, endereco);
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
