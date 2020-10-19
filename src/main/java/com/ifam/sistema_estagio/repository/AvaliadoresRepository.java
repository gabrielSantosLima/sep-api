package com.ifam.sistema_estagio.repository;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifam.sistema_estagio.entity.Avaliadores;
import com.ifam.sistema_estagio.entity.Banca;

@Repository
public interface AvaliadoresRepository extends JpaRepository<Avaliadores, Integer>{
	
	List<Avaliadores> findByBanca(Banca banca);
}
