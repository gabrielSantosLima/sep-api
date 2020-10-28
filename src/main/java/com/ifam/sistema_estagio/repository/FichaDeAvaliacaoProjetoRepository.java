package com.ifam.sistema_estagio.repository;

import org.springframework.stereotype.Repository;

import com.ifam.sistema_estagio.entity.Ata;
import com.ifam.sistema_estagio.entity.FichaDeAvaliacaoProjeto;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface FichaDeAvaliacaoProjetoRepository extends JpaRepository<FichaDeAvaliacaoProjeto, Integer>{

	List<FichaDeAvaliacaoProjeto> findByAta(Ata ata);
	
}
