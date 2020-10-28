package com.ifam.sistema_estagio.services;

import java.util.Optional;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ifam.sistema_estagio.entity.Professor;
import com.ifam.sistema_estagio.repository.PapelRepository;
import com.ifam.sistema_estagio.repository.ProfessorRepository;

@Service
public class ProfessorService extends GenericService<Professor, ProfessorRepository> {

	@Autowired
	private ProfessorRepository repository;

	@Autowired
	private PapelRepository papelRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPassowrdEncoder;

	public Professor create(Professor e, String papel) throws HibernateException {
		e.setPassword(bCryptPassowrdEncoder.encode(e.getPassword()));
		return repository.save(e);
	}

	public Optional<Professor> findByUsername(String username) {
		return repository.findByUsername(username);
	}
}
