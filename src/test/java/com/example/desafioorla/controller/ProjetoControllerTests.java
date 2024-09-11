package com.example.desafioorla.controller;

import com.example.desafioorla.entity.Projeto;
import com.example.desafioorla.service.ProjetoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(ProjetoController.class)
public class ProjetoControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProjetoService projetoService;

    @Test
    public void criarProjeto() throws Exception {
        Projeto projeto = new Projeto();
        projeto.setNome("Novo Projeto Teste");
        projeto.setDataCriacao(LocalDate.now());

        Mockito.when(projetoService.saveProjeto(Mockito.any(Projeto.class))).thenReturn(projeto);

        mockMvc.perform(post("/projetos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\": \"Novo Projeto Teste\", \"dataCriacao\": \"2024-09-11\"}"))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Novo Projeto Teste"));
    }
}
