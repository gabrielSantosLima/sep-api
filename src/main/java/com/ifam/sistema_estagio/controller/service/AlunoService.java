package com.ifam.sistema_estagio.controller.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ifam.sistema_estagio.model.entity.Aluno;
import com.ifam.sistema_estagio.model.entity.EstagioPCCT;
import com.ifam.sistema_estagio.model.repository.AlunoRepository;
import com.ifam.sistema_estagio.model.repository.PapelRepository;

@Service
public class AlunoService extends GenericService<Aluno, AlunoRepository>{
	
	@Autowired
	private AlunoRepository repository;
	
	@Autowired
	private PapelRepository papelRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPassowrdEncoder;

	public Aluno create(Aluno e, String papel) throws HibernateException {
		e.setPassword(bCryptPassowrdEncoder.encode(e.getPassword()));
		e.setPapel(papelRepository.findByName(papel).get());
		return repository.save(e);
	}

	
	public List<Aluno> findByEstagioPcct(EstagioPCCT estagioPcct){
		return repository.findByEstagioPcct(estagioPcct);
	}
	
	public Optional<Aluno> findByUsername(String username){
		return repository.findByUsername(username);
	} 
}
