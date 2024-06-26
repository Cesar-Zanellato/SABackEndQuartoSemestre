package com.back.fortesupermercados.controller;

import com.back.fortesupermercados.dtos.token.LoginInput;
import com.back.fortesupermercados.services.TokenService;
import com.back.fortesupermercados.controllers.LoginController;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(LoginController.class)
public class LoginControllerTest {

    private MockMvc mockMvc;

    @Mock
    private TokenService tokenService;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private LoginController loginController;

    private static final String VALID_EMAIL = "joao.silva@teste.com";
    private static final String VALID_PASSWORD = "senha123";
    private static final String INVALID_PASSWORD = "senha-incorreta";
    private static final String MOCK_TOKEN = "mocked-jwt-token";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testLogin_Success() throws Exception {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                VALID_EMAIL, VALID_PASSWORD);
        Mockito.when(authenticationManager.authenticate(Mockito.any())).thenReturn(authenticationToken);

        Mockito.when(tokenService.createToken(Mockito.any())).thenReturn(MOCK_TOKEN);

        mockMvc.perform(MockMvcRequestBuilders.post("/login")
                .contentType("application/json")
                .content("{\"email\": \"" + VALID_EMAIL + "\", \"password\": \"" + VALID_PASSWORD + "\"}")
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/plain;charset=UTF-8"))
                .andExpect(content().string(MOCK_TOKEN));
    }

    @Test
    public void testLogin_Unauthorized() throws Exception {
        Mockito.when(authenticationManager.authenticate(Mockito.any()))
               .thenThrow(new UsernameNotFoundException("User not found"));

        mockMvc.perform(MockMvcRequestBuilders.post("/login")
                .contentType("application/json")
                .content("{\"email\": \"" + VALID_EMAIL + "\", \"password\": \"" + INVALID_PASSWORD + "\"}")
        )
                .andExpect(status().isUnauthorized())
                .andExpect(content().string(""));
    }

    @Test
    public void testLogin_InvalidEmail() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/login")
                .contentType("application/json")
                .content("{\"email\": \"email-invalido\", \"password\": \"" + VALID_PASSWORD + "\"}")
        )
                .andExpect(status().isBadRequest());
    }

}
