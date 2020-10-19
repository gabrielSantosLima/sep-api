package com.ifam.sistema_estagio.repository;

import org.springframework.stereotype.Repository;

import com.ifam.sistema_estagio.entity.Professor;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Integer>{
	
	Optional<Professor> findByUsername(String username);
}
