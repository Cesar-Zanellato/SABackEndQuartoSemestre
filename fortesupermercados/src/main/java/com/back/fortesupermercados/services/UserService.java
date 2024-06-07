package com.back.fortesupermercados.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.back.fortesupermercados.dtos.users.UserInput;
import com.back.fortesupermercados.dtos.users.UserOutput;
import com.back.fortesupermercados.entities.User;
import com.back.fortesupermercados.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService{
    @Autowired
    private UserRepository repository;

    @Transactional
    public UserOutput create(UserInput input){
        User user = convertInputToUser(input);
        user = repository.save(user);
        return convertUserToOutput(user);
    }

    public List<UserOutput> list(){
        return repository
        .findAll()
        .stream()
        .map(user -> convertUserToOutput(user))
        .toList();
    }

    public UserOutput read(Long id){
        User user = repository.findById(id).orElse(null);
        return convertUserToOutput(user);
    }

    @Transactional
    public UserOutput update(Long id, UserInput input){
        if(repository.existsById(id)){
            User user = convertInputToUser(input);
            user.setId(id);
            user = repository.save(user);
            return convertUserToOutput(user);
        }else{
            return null;
        }
    }

    @Transactional
    public void delete(Long id){
        repository.deleteById(id);
    }

    private UserOutput convertUserToOutput(User user){
        if(user == null){
            return null;
        }
        UserOutput output = new UserOutput(
            user.getId(), 
            user.getName(),
            user.getEmail(), 
            user.getPhone(), 
            user.getCpf(), 
            user.getAddress(),
            user.getIsAdmin()
        );

        return output;
    }

    private User convertInputToUser(UserInput input){
        User user = new User();
        user.setName(input.name());
        user.setEmail(input.email());
        user.setPhone(input.phone());
        user.setCpf(input.cpf());
        var senhaCriptografa = new BCryptPasswordEncoder().encode(input.password());
        user.setPassword(senhaCriptografa);

        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = repository.findByEmail(email);
        if(user == null){
            throw new UsernameNotFoundException("User not found");
        }

        return org.springframework.security.core.userdetails.User
                .builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .build();
    }
}