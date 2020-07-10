package com.ifam.sistema_estagio.model.repository;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifam.sistema_estagio.model.entity.Avaliadores;
import com.ifam.sistema_estagio.model.entity.Banca;

@Repository
public interface AvaliadoresRepository extends JpaRepository<Avaliadores, Integer>{
	
	List<Avaliadores> findByBanca(Banca banca);
}
