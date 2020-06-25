package com.ifam.sistema_estagio.controller.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifam.sistema_estagio.model.entity.Ata;
import com.ifam.sistema_estagio.model.entity.Banca;
import com.ifam.sistema_estagio.model.repository.AtaRepository;

@Service
public class AtaService extends GenericService<Ata, AtaRepository>{
	
	@Autowired
	private AtaRepository repository;
	
	public List<Ata> findByBanca(Banca banca){
		return repository.findByBanca(banca);
	}
}
