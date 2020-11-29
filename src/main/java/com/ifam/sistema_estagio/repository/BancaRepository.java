package com.ifam.sistema_estagio.repository;

import java.util.List;

import com.ifam.sistema_estagio.util.enums.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifam.sistema_estagio.entity.Banca;

@Repository
public interface BancaRepository extends JpaRepository<Banca, String>{
	List<Banca> findByCurso(Curso curso);
}
