package com.example.desafioorla.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "projetos")
public class Projeto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "Nome do projeto não pode ser vazio")
    private String nome;

    @Column(nullable = false)
    @NotNull(message = "Data de criação não pode ser nula")
    private LocalDate dataCriacao;

    @ManyToMany
    @JoinTable(
            name = "funcionario_projeto",
            joinColumns = @JoinColumn(name = "projeto_id"),
            inverseJoinColumns = @JoinColumn(name = "funcionario_id")
    )
    @JsonIgnoreProperties("projetos")
    private Set<Funcionario> funcionarios = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Set<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(Set<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }
}
