package com.ifam.sistema_estagio.controller.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifam.sistema_estagio.model.entity.Estagio;
import com.ifam.sistema_estagio.model.entity.EstagioPCCT;
import com.ifam.sistema_estagio.model.entity.Projeto;

@Service
public class EstagioPcctService{
	
	@Autowired
	private EstagioService estagioService;
	
	@Autowired
	private ProjetoService projetoService;
	
	//Encontrar entidade por id, podendo ela ser um estagio ou um projeto
	public EstagioPCCT findByEstagioOrProjetoId(Integer id) {
		Optional<Estagio> estagio = estagioService.findById(id);
		
		if(!estagio.isPresent()) {
			Optional<Projeto> projeto = projetoService.findById(id);
			return projeto.get();
		}
		
		return estagio.get();
	}
}
