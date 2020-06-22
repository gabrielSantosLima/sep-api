package com.ifam.sistema_estagio.controller.service;

import java.util.HashSet;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ifam.sistema_estagio.model.entity.Coordenadora;
import com.ifam.sistema_estagio.model.repository.CoordenadoraRepository;
import com.ifam.sistema_estagio.model.repository.RoleRepository;

@Service
public class CoordenadoraService extends GenericService<Coordenadora, CoordenadoraRepository> {

	@Autowired
	private CoordenadoraRepository repository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPassowrdEncoder;

	@Override
	public Coordenadora create(Coordenadora e) throws HibernateException {
		e.setPassword(bCryptPassowrdEncoder.encode(e.getPassword()));
		e.setRoles(new HashSet<>(roleRepository.findAll()));
		return repository.save(e);
	}

	public Coordenadora findByUsername(String username) {
		return repository.findByUsername(username);
	}
}
