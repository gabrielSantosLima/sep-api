package com.ifam.sistema_estagio.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifam.sistema_estagio.entity.Funcao;

@Repository
public interface FuncaoRepository extends JpaRepository<Funcao, Integer>{
	
	Optional<Funcao> findByNome(String nome); 
}
