package com.example.desafioorla.service;

import com.example.desafioorla.entity.Funcionario;
import com.example.desafioorla.repository.FuncionarioRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class FuncionarioServiceTests {

    @Autowired
    private FuncionarioService funcionarioService;

    @MockBean
    private FuncionarioRepository funcionarioRepository;

    @Test
    public void salvarFuncionario() {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome("Funcionario Teste");
        funcionario.setCpf("12345678901");
        funcionario.setEmail("funcionario@teste.com");
        funcionario.setSalario(BigDecimal.valueOf(1500.00));

        Mockito.when(funcionarioRepository.save(funcionario)).thenReturn(funcionario);

        Funcionario savedFuncionario = funcionarioService.saveFuncionario(funcionario);
        assertThat(savedFuncionario.getNome()).isEqualTo("Funcionario Teste");
    }

    @Test
    public void listarTodosFuncionarios() {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome("Funcionario Teste");
        funcionario.setCpf("12345678901");
        funcionario.setEmail("funcionario@teste.com");
        funcionario.setSalario(BigDecimal.valueOf(1500.00));

        Mockito.when(funcionarioRepository.findAll()).thenReturn(Collections.singletonList(funcionario));

        assertThat(funcionarioService.getAllFuncionarios()).hasSize(1);
    }

    @Test
    public void buscarFuncionarioPorId() {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome("Funcionario Teste");
        funcionario.setCpf("12345678901");
        funcionario.setEmail("funcionario@teste.com");
        funcionario.setSalario(BigDecimal.valueOf(1500.00));

        Mockito.when(funcionarioRepository.findById(1L)).thenReturn(Optional.of(funcionario));

        Optional<Funcionario> retrievedFuncionario = funcionarioService.getFuncionarioById(1L);
        assertThat(retrievedFuncionario).isPresent();
        assertThat(retrievedFuncionario.get().getNome()).isEqualTo("Funcionario Teste");
    }

    @Test
    public void excluirFuncionario() {
        funcionarioService.deleteFuncionario(1L);
        Mockito.verify(funcionarioRepository).deleteById(1L);
    }
}
