package com.ifam.sistema_estagio.model.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ifam.sistema_estagio.model.entity.Coordenadora;

@Repository
public interface CoordenadoraRepository extends JpaRepository<Coordenadora, Integer>{

}
