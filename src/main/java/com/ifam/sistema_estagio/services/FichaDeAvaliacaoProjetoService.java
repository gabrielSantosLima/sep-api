package com.ifam.sistema_estagio.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifam.sistema_estagio.entity.Ata;
import com.ifam.sistema_estagio.entity.FichaDeAvaliacaoProjeto;
import com.ifam.sistema_estagio.repository.FichaDeAvaliacaoProjetoRepository;

@Service
public class FichaDeAvaliacaoProjetoService extends GenericService<FichaDeAvaliacaoProjeto,FichaDeAvaliacaoProjetoRepository>{
	
	@Autowired
	private FichaDeAvaliacaoProjetoRepository repository;

	public List<FichaDeAvaliacaoProjeto> findByAta(Ata ata){
		return repository.findByAta(ata);
	}
}
