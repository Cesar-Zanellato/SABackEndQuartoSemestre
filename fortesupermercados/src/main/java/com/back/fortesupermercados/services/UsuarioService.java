package com.back.fortesupermercados.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.back.fortesupermercados.dtos.usuarios.UsuarioEntrada;
import com.back.fortesupermercados.dtos.usuarios.UsuarioSaida;
import com.back.fortesupermercados.entities.Usuario;
import com.back.fortesupermercados.repositories.UsuarioRepository;

@Service
public class UsuarioService implements UserDetailsService{
    @Autowired
    UsuarioRepository repository;

    @Transactional
    public UsuarioSaida create(UsuarioEntrada entrada){
        Usuario usuario = convertEntradaParaUsuario(entrada);
        usuario = repository.save(usuario);

        return convertUsuarioParaSaida(usuario);
    }

    public List<UsuarioSaida> list(){
        return repository
        .findAll()
        .stream()
        .map(usuario -> convertUsuarioParaSaida(usuario))
        .toList();
    }

    public UsuarioSaida read(Long id){
        Usuario usuario = repository.findById(id).orElse(null);
        return convertUsuarioParaSaida(usuario);
    }

    @Transactional
    public UsuarioSaida update(Long id, UsuarioEntrada entrada){
        if(repository.existsById(id)){
            Usuario usuario = convertEntradaParaUsuario(entrada);
            usuario.setId(id);
            usuario = repository.save(usuario);
            return convertUsuarioParaSaida(usuario);
        }else{
            return null;
        }
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

    private UsuarioSaida convertUsuarioParaSaida(Usuario usuario){
        if(usuario == null){
            return null;
        }
        UsuarioSaida saida = new UsuarioSaida(
            usuario.getId(), 
            usuario.getNome(), 
            usuario.getEmail(), 
            usuario.getTelefone(), 
            usuario.getCpf(), 
            usuario.getEndereco(), 
            usuario.getPedidos()
        );

        return saida;
    }

    private Usuario convertEntradaParaUsuario(UsuarioEntrada entrada){
        Usuario usuario = new Usuario();
        usuario.setNome(entrada.nome());
        usuario.setEmail(entrada.email());
        usuario.setTelefone(entrada.telefone());
        usuario.setCpf(entrada.cpf());
        usuario.setSenha(entrada.senha());

        return usuario;
    }

    @Override
    public UserDetails loadUserByUsername(String cpf) throws UsernameNotFoundException {
        Usuario usuario = repository.findByCpf(cpf);
        if(usuario == null){
            throw new UsernameNotFoundException("Usuario not found");
        }

        return User
                .builder()
                .username(usuario.getUsername())
                .password(usuario.getPassword())
                .build();
    }



    // public List<Usuario> hasUsuario(List<Usuario> usuarios) throws Exception{
    //     if (usuarios.size() == 0){
    //         return null;
    //         // throw new Exception("Lista de usuarios vazia");
    //     }else{
    //         return usuarios;
    //     }

    // }
}