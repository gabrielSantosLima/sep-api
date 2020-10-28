package com.ifam.sistema_estagio.services;

import java.util.List;
import java.util.Optional;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ifam.sistema_estagio.entity.Aluno;
import com.ifam.sistema_estagio.entity.EstagioPCCT;
import com.ifam.sistema_estagio.repository.AlunoRepository;
import com.ifam.sistema_estagio.repository.PapelRepository;

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
		return repository.save(e);
	}

	
	public List<Aluno> findByEstagioPcct(EstagioPCCT estagioPcct){
		return repository.findByEstagioPcct(estagioPcct);
	}
	
	public Optional<Aluno> findByUsername(String username){
		return repository.findByUsername(username);
	} 
}
