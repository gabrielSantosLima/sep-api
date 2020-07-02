package com.ifam.sistema_estagio.model.repository;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ifam.sistema_estagio.model.entity.Ata;
import com.ifam.sistema_estagio.model.entity.FichaDeAvaliacaoProjeto;

@Repository
public interface FichaDeAvaliacaoProjetoRepository extends JpaRepository<FichaDeAvaliacaoProjeto, Integer>{

	List<FichaDeAvaliacaoProjeto> findByAta(Ata ata);
	
}
