package com.ifam.sistema_estagio.model.repository;

import org.springframework.stereotype.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ifam.sistema_estagio.model.entity.Papel;

@Repository
public interface PapelRepository extends JpaRepository<Papel, Integer>{
	
	Optional<Papel> findByName(String name); 
}
