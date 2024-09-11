package com.example.desafioorla.controller;

import com.example.desafioorla.entity.Funcionario;
import com.example.desafioorla.service.FuncionarioService;
import com.example.desafioorla.service.ProjetoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;

@WebMvcTest(FuncionarioController.class)
public class FuncionarioControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FuncionarioService funcionarioService;

    @MockBean
    private ProjetoService projetoService;

    @Test
    public void criarFuncionario() throws Exception {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome("Funcionario Teste");
        funcionario.setCpf("12345678901");
        funcionario.setEmail("funcionario@teste.com");
        funcionario.setSalario(BigDecimal.valueOf(1500.00));

        Mockito.when(funcionarioService.saveFuncionario(Mockito.any(Funcionario.class))).thenReturn(funcionario);

        mockMvc.perform(MockMvcRequestBuilders.post("/funcionarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\": \"Funcionario Teste\", \"cpf\": \"12345678901\", \"email\": \"funcionario@teste.com\", \"salario\": 1500.00}"))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Funcionario Teste"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cpf").value("12345678901"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("funcionario@teste.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.salario").value(1500.00));
    }
}
