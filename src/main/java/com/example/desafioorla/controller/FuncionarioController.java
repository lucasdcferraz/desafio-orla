package com.example.desafioorla.controller;

import com.example.desafioorla.entity.Funcionario;
import com.example.desafioorla.entity.Projeto;
import com.example.desafioorla.service.FuncionarioService;
import com.example.desafioorla.service.ProjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/funcionarios")
@Validated
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @Autowired
    private ProjetoService projetoService;

    @PostMapping("/{funcionarioId}/projetos/{projetoId}")
    public ResponseEntity<Funcionario> addProjetoToFuncionario(
            @PathVariable Long funcionarioId,
            @PathVariable Long projetoId) {

        Optional<Funcionario> funcionarioOpt = funcionarioService.getFuncionarioById(funcionarioId);
        Optional<Projeto> projetoOpt = projetoService.getProjetoById(projetoId);

        if (funcionarioOpt.isEmpty() || projetoOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Funcionario funcionarioToUpdate = funcionarioOpt.get();
        Projeto projetoToAdd = projetoOpt.get();

        if (funcionarioToUpdate.getProjetos().contains(projetoToAdd)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        funcionarioToUpdate.getProjetos().add(projetoToAdd);
        Funcionario updatedFuncionario = funcionarioService.saveFuncionario(funcionarioToUpdate);

        return ResponseEntity.ok(updatedFuncionario);
    }

    @GetMapping
    public ResponseEntity<List<Funcionario>> getAllFuncionarios() {
        List<Funcionario> funcionarios = funcionarioService.getAllFuncionarios();
        return ResponseEntity.ok(funcionarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Funcionario> getFuncionarioById(@PathVariable Long id) {
        Optional<Funcionario> funcionario = funcionarioService.getFuncionarioById(id);
        return funcionario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Funcionario> createFuncionario(@Valid @RequestBody Funcionario funcionario) {
        Funcionario savedFuncionario = funcionarioService.saveFuncionario(funcionario);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedFuncionario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Funcionario> updateFuncionario(
            @PathVariable Long id,
            @Valid @RequestBody Funcionario funcionario) {

        Optional<Funcionario> existingFuncionario = funcionarioService.getFuncionarioById(id);
        if (!existingFuncionario.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        funcionario.setId(id);
        Funcionario updatedFuncionario = funcionarioService.saveFuncionario(funcionario);
        return ResponseEntity.ok(updatedFuncionario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFuncionario(@PathVariable Long id) {
        Optional<Funcionario> funcionario = funcionarioService.getFuncionarioById(id);
        if (!funcionario.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        funcionarioService.deleteFuncionario(id);
        return ResponseEntity.noContent().build();
    }
}
