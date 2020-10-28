package com.ifam.sistema_estagio.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifam.sistema_estagio.entity.Ata;
import com.ifam.sistema_estagio.entity.FichaDeAvaliacaoEstagio;
import com.ifam.sistema_estagio.repository.FichaDeAvaliacaoEstagioRepository;

@Service
public class FichaDeAvaliacaoEstagioService 
	extends GenericService<FichaDeAvaliacaoEstagio, FichaDeAvaliacaoEstagioRepository>{

	@Autowired
	private FichaDeAvaliacaoEstagioRepository repository;
	
	public List<FichaDeAvaliacaoEstagio> findByAta(Ata ata){
		return repository.findByAta(ata);
	}
}
