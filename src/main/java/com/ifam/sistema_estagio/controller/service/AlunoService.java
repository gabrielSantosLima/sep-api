package com.ifam.sistema_estagio.controller.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifam.sistema_estagio.model.entity.Aluno;
import com.ifam.sistema_estagio.model.entity.EstagioPCCT;
import com.ifam.sistema_estagio.model.repository.AlunoRepository;

@Service
public class AlunoService extends GenericService<Aluno, AlunoRepository>{
	
	@Autowired
	private AlunoRepository alunoRepository;
	
	public List<Aluno> findByEstagioPcct(EstagioPCCT estagioPcct){
		return alunoRepository.findByEstagioPcct(estagioPcct);
	}
}
