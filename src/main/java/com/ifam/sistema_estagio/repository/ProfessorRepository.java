package com.ifam.sistema_estagio.repository;

import org.springframework.stereotype.Repository;

import com.ifam.sistema_estagio.entity.Professor;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, String>{}
