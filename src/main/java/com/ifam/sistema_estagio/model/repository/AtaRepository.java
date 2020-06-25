package com.ifam.sistema_estagio.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifam.sistema_estagio.model.entity.Ata;
import com.ifam.sistema_estagio.model.entity.Banca;

@Repository
public interface AtaRepository extends JpaRepository<Ata, Integer>{
	
	List<Ata> findByBanca(Banca banca); 
}
