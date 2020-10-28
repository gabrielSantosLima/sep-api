package com.ifam.sistema_estagio.repository;

import org.springframework.stereotype.Repository;

import com.ifam.sistema_estagio.entity.Papel;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface PapelRepository extends JpaRepository<Papel, Integer>{
	
	Optional<Papel> findByNome(String nome); 
}
