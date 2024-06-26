package com.back.fortesupermercados.controller;

import com.back.fortesupermercados.dtos.users.UserInput;
import com.back.fortesupermercados.dtos.users.UserOutput;
import com.back.fortesupermercados.services.UserService;
import com.back.fortesupermercados.controllers.UserController;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Test
    public void testCreateUser() throws Exception {
        UserInput userInput = new UserInput(
                "João da Silva",
                "joao.silva@teste.com",
                "999999999",
                "12345678901",
                "senha123"
        );

        UserOutput userOutput = new UserOutput(
                1L,
                userInput.name(),
                userInput.email(),
                userInput.phone(),
                userInput.cpf(),
                null, 
                false 
        );
        when(userService.create(any())).thenReturn(userOutput);

        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(userInput))
        )
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value(userInput.name()))
                .andExpect(jsonPath("$.email").value(userInput.email()))
                .andExpect(jsonPath("$.phone").value(userInput.phone()))
                .andExpect(jsonPath("$.cpf").value(userInput.cpf()));
    }
}
