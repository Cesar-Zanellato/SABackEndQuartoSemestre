package com.back.fortesupermercados.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.back.fortesupermercados.entities.Usuario;


public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    
    Usuario findByCpf(String cpf);
}
