package com.ifam.sistema_estagio.model.repository;

import org.springframework.stereotype.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ifam.sistema_estagio.model.entity.Professor;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Integer>{
	
	Optional<Professor> findByUsername(String username);
}
