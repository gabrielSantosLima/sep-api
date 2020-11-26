package com.ifam.sistema_estagio.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifam.sistema_estagio.entity.Aluno;
import com.ifam.sistema_estagio.entity.EstagioPCCT;
import com.ifam.sistema_estagio.repository.AlunoRepository;

@Service
public class AlunoService extends GenericService<Aluno, AlunoRepository>{
	
	@Autowired
	private AlunoRepository repository;

	public List<Aluno> findByEstagioPcct(EstagioPCCT estagioPcct){
		return repository.findByEstagioPcct(estagioPcct);
	}
}
