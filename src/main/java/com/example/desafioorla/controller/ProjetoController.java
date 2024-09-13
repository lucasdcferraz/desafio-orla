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
@RequestMapping("/projetos")
@Validated
public class ProjetoController {

    @Autowired
    private ProjetoService projetoService;

    @Autowired
    private FuncionarioService funcionarioService;

    @PostMapping("/{projetoId}/funcionarios/{funcionarioId}")
    public ResponseEntity<Projeto> addFuncionarioToProjeto(
            @PathVariable Long projetoId,
            @PathVariable Long funcionarioId) {

        Optional<Projeto> projetoOpt = projetoService.getProjetoById(projetoId);
        Optional<Funcionario> funcionarioOpt = funcionarioService.getFuncionarioById(funcionarioId);

        if (projetoOpt.isEmpty() || funcionarioOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Projeto projetoToUpdate = projetoOpt.get();
        Funcionario funcionarioToAdd = funcionarioOpt.get();

        if (projetoToUpdate.getFuncionarios().contains(funcionarioToAdd)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        projetoToUpdate.getFuncionarios().add(funcionarioToAdd);
        Projeto updatedProjeto = projetoService.saveProjeto(projetoToUpdate);

        return ResponseEntity.ok(updatedProjeto);
    }

    @GetMapping
    public ResponseEntity<List<Projeto>> getAllProjetos() {
        List<Projeto> projetos = projetoService.getAllProjetos();
        return ResponseEntity.ok(projetos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Projeto> getProjetoById(@PathVariable Long id) {
        Optional<Projeto> projeto = projetoService.getProjetoById(id);
        return projeto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Projeto> createProjeto(@Valid @RequestBody Projeto projeto) {
        Projeto savedProjeto = projetoService.saveProjeto(projeto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProjeto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Projeto> updateProjeto(
            @PathVariable Long id,
            @Valid @RequestBody Projeto projeto) {

        Optional<Projeto> existingProjeto = projetoService.getProjetoById(id);
        if (!existingProjeto.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        projeto.setId(id);
        Projeto updatedProjeto = projetoService.saveProjeto(projeto);
        return ResponseEntity.ok(updatedProjeto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProjeto(@PathVariable Long id) {
        Optional<Projeto> projeto = projetoService.getProjetoById(id);
        if (!projeto.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        projetoService.deleteProjeto(id);
        return ResponseEntity.noContent().build();
    }
}
