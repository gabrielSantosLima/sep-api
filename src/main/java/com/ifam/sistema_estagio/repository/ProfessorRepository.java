package com.ifam.sistema_estagio.repository;

import org.springframework.stereotype.Repository;

import com.ifam.sistema_estagio.entity.Professor;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, String>{
    List<Professor> findByNomeContainingIgnoreCase(String nome);
    List<Professor> findByMatriculaContainingIgnoreCase(String matricula);
    Optional<Professor> findByCpf(String cpf);
}
