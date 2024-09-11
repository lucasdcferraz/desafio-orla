package com.example.desafioorla.service;

import com.example.desafioorla.entity.Projeto;
import com.example.desafioorla.repository.ProjetoRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ProjetoServiceTests {

    @Autowired
    private ProjetoService projetoService;

    @MockBean
    private ProjetoRepository projetoRepository;

    @Test
    public void salvarProjeto() {
        Projeto projeto = new Projeto();
        projeto.setNome("Novo Projeto Teste");
        projeto.setDataCriacao(LocalDate.now());

        Mockito.when(projetoRepository.save(projeto)).thenReturn(projeto);

        Projeto savedProjeto = projetoService.saveProjeto(projeto);
        assertThat(savedProjeto.getNome()).isEqualTo("Novo Projeto Teste");
    }

    @Test
    public void listarTodosProjetos() {
        Projeto projeto = new Projeto();
        projeto.setNome("Novo Projeto Teste");
        projeto.setDataCriacao(LocalDate.now());

        Mockito.when(projetoRepository.findAll()).thenReturn(Collections.singletonList(projeto));

        assertThat(projetoService.getAllProjetos()).hasSize(1);
    }

    @Test
    public void buscarProjetoPorId() {
        Projeto projeto = new Projeto();
        projeto.setNome("Novo Projeto Teste");
        projeto.setDataCriacao(LocalDate.now());

        Mockito.when(projetoRepository.findById(1L)).thenReturn(Optional.of(projeto));

        Optional<Projeto> retrievedProjeto = projetoService.getProjetoById(1L);
        assertThat(retrievedProjeto).isPresent();
        assertThat(retrievedProjeto.get().getNome()).isEqualTo("Novo Projeto Teste");
    }

    @Test
    public void excluirProjeto() {
        Projeto projeto = new Projeto();
        projeto.setNome("Novo Projeto");
        projeto.setDataCriacao(LocalDate.now());

        projetoService.deleteProjeto(1L);
        Mockito.verify(projetoRepository).deleteById(1L);
    }
}
