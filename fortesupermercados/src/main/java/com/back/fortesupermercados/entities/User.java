package com.back.fortesupermercados.entities;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity @Data @NoArgsConstructor @AllArgsConstructor
public class User implements UserDetails{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true)
    private String email;
    private String phone;
    private String cpf;
    private String password;
    @OneToOne(mappedBy = "user", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Address address;
    @OneToOne
    private Shopping shopping;
    

    public void setAddress(Address address) {
        this.address = address;
        address.setUser(this);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;    
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;    
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;    
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;    
    }

    @Override
    public String getUsername() {
        return name;    
    }
}
