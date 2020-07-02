package com.ifam.sistema_estagio.model.repository;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ifam.sistema_estagio.model.entity.Ata;
import com.ifam.sistema_estagio.model.entity.FichaDeAvaliacaoEstagio;

@Repository
public interface FichaDeAvaliacaoEstagioRepository extends JpaRepository<FichaDeAvaliacaoEstagio, Integer>{

	List<FichaDeAvaliacaoEstagio> findByAta(Ata ata);
}
