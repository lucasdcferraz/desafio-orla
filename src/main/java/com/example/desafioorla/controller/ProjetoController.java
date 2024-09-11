package com.example.desafioorla.controller;

import com.example.desafioorla.entity.Projeto;
import com.example.desafioorla.service.ProjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/projetos")
public class ProjetoController {

    @Autowired
    private ProjetoService projetoService;

    @PostMapping
    public ResponseEntity<Projeto> createProjeto(@RequestBody Projeto projeto) {
        Projeto savedProjeto = projetoService.saveProjeto(projeto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProjeto);
    }

    @GetMapping
    public List<Projeto> getAllProjetos() {
        return projetoService.getAllProjetos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Projeto> getProjetoById(@PathVariable Long id) {
        Optional<Projeto> projeto = projetoService.getProjetoById(id);
        return projeto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Projeto> updateProjeto(@PathVariable Long id, @RequestBody Projeto projeto) {
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
        projetoService.deleteProjeto(id);
        return ResponseEntity.noContent().build();
    }
}
