package com.example.desafioorla.service;

import com.example.desafioorla.entity.Projeto;
import com.example.desafioorla.repository.ProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjetoService {

    @Autowired
    private ProjetoRepository projetoRepository;

    public Projeto saveProjeto(Projeto projeto) {
        return projetoRepository.save(projeto);
    }

    public List<Projeto> getAllProjetos() {
        return projetoRepository.findAll();
    }

    public Optional<Projeto> getProjetoById(Long id) {
        return projetoRepository.findById(id);
    }

    public void deleteProjeto(Long id) {
        projetoRepository.deleteById(id);
    }
}
