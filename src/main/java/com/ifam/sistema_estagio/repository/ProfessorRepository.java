package com.ifam.sistema_estagio.repository;

import org.springframework.stereotype.Repository;

import com.ifam.sistema_estagio.entity.Professor;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, String>{
    List<Professor> findByNomeContainingIgnoreCase(String nome);
    List<Professor> findByMatriculaContainingIgnoreCase(String matricula);
    List<Professor> findByCpf(String cpf);
}
