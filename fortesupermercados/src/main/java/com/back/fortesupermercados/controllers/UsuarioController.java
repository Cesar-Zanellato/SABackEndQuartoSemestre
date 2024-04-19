package com.back.fortesupermercados.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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
import com.back.fortesupermercados.entities.Usuario;
import com.back.fortesupermercados.repositories.UsuarioRepository;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    
    @Autowired
    private UsuarioRepository repository;

    @GetMapping
    public ResponseEntity<List<Usuario>> list(){

        List<Usuario> list = repository.findAll();
        return ResponseEntity.ok(list);
    }

    // @GetMapping
    // public ResponseEntity list(){
    //     try {
    //         return ResponseEntity.ok().build();
    //     } catch (Exception ex) {
    //         return ResponseEntity.status(204).build();
    //     }
    // }
    
    @PostMapping
    public ResponseEntity<UsuarioSaida> create(@RequestBody UsuarioEntrada usuario){
        Usuario usuarioParaCadastrar = new Usuario();
        usuarioParaCadastrar.setNome(usuario.getNome());
        usuarioParaCadastrar.setEmail(usuario.getEmail());
        usuarioParaCadastrar.setTelefone(usuario.getTelefone());
        usuarioParaCadastrar.setCpf(usuario.getCpf());
        usuarioParaCadastrar.setSenha(usuario.getSenha());

        Usuario usuarioCadastrado = repository.save(usuarioParaCadastrar);

        UsuarioSaida saida = new UsuarioSaida(
            usuarioCadastrado.getId(), 
            usuarioCadastrado.getNome(), 
            usuarioCadastrado.getEmail(), 
            usuarioCadastrado.getTelefone(), 
            usuarioCadastrado.getCpf(), 
            usuarioCadastrado.getEndereco(), 
            usuarioCadastrado.getPedidos()
        );

        return new ResponseEntity(saida, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> read(@PathVariable Long id){

        Usuario usuario = repository.findById(id).get();
        return ResponseEntity.ok(usuario);
    }

    @PutMapping
    public ResponseEntity<Usuario> update(@RequestBody Usuario usuario){
        Usuario usuarioAtualizado = repository.save(usuario);
        return ResponseEntity.ok(usuarioAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}