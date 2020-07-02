package com.ifam.sistema_estagio.controller.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifam.sistema_estagio.model.entity.Ata;
import com.ifam.sistema_estagio.model.entity.FichaDeAvaliacaoProjeto;
import com.ifam.sistema_estagio.model.repository.FichaDeAvaliacaoProjetoRepository;

@Service
public class FichaDeAvaliacaoProjetoService extends GenericService<FichaDeAvaliacaoProjeto,FichaDeAvaliacaoProjetoRepository>{
	
	@Autowired
	private FichaDeAvaliacaoProjetoRepository repository;

	public List<FichaDeAvaliacaoProjeto> findByAta(Ata ata){
		return repository.findByAta(ata);
	}
}
