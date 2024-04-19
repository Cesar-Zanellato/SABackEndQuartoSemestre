package com.back.fortesupermercados.services;
// package com.back.fortesupermercados.service;

// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import com.back.fortesupermercados.entities.Usuario;
// import com.back.fortesupermercados.repositories.UsuarioRepository;

// @Service
// public class UsuarioService {
//     @Autowired
//     UsuarioRepository usuarioRepository;

//     public List<Usuario> getAllUsuarios() throws Exception{

//         List<Usuario> allUsuarios;

//         try {
//             allUsuarios = usuarioRepository.findAll();
//         } catch(Exception ex){
//             allUsuarios = List.of();
//         }

//         if (allUsuarios.size() == 0){
//             throw new Exception("Lista de usuarios vazia");
//         }else{
//             return hasUsuario(allUsuarios);
//         }
//     }


//     public List<Usuario> hasUsuario(List<Usuario> usuarios) throws Exception{
//         if (usuarios.size() == 0){
//             return null;
//             // throw new Exception("Lista de usuarios vazia");
//         }else{
//             return usuarios;
//         }

//     }
// }



