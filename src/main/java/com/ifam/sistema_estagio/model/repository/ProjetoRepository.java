package com.ifam.sistema_estagio.model.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ifam.sistema_estagio.model.entity.Projeto;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Integer>{
}
