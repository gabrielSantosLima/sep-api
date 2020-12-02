package com.ifam.sistema_estagio.repository;

import org.springframework.stereotype.Repository;

import com.ifam.sistema_estagio.entity.Ata;
import com.ifam.sistema_estagio.entity.FichaDeAvaliacaoEstagio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface FichaDeAvaliacaoEstagioRepository extends JpaRepository<FichaDeAvaliacaoEstagio, String>{
	List<FichaDeAvaliacaoEstagio> findByAta(Ata ata);
}
