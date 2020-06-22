package com.ifam.sistema_estagio.controller.service;

import java.util.HashSet;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ifam.sistema_estagio.model.entity.Professor;
import com.ifam.sistema_estagio.model.repository.ProfessorRepository;
import com.ifam.sistema_estagio.model.repository.RoleRepository;

@Service
public class ProfessorService extends GenericService<Professor, ProfessorRepository> {

	@Autowired
	private ProfessorRepository repository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPassowrdEncoder;

	@Override
	public Professor create(Professor e) throws HibernateException {
		e.setPassword(bCryptPassowrdEncoder.encode(e.getPassword()));
		e.setRoles(new HashSet<>(roleRepository.findAll()));
		return repository.save(e);
	}

	public Professor findByUsername(String username) {
		return repository.findByUsername(username);
	}
}
