package com.back.fortesupermercados.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest(classes = {})
public class UsuarioServiceTest {
    
    protected double fValue1 = 2.0;
    protected double fValue2 = 3.0;

    @Test
    void testAdd(){
        assertEquals(fValue1 + fValue2, 5.0);
    }

    

    
    // @Test
    // public void testListUsuarioSizeEqualsZero(){
    //     var service = new UsuarioService();

    //     List<Usuario> usuarios;

    //     service.hasUsuario(null);

        
    // } 
}
