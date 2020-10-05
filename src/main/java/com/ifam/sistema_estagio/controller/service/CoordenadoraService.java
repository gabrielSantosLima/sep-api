package com.ifam.sistema_estagio.controller.service;

import java.util.Optional;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ifam.sistema_estagio.model.entity.Coordenadora;
import com.ifam.sistema_estagio.model.repository.CoordenadoraRepository;
import com.ifam.sistema_estagio.model.repository.PapelRepository;

@Service
public class CoordenadoraService extends GenericService<Coordenadora, CoordenadoraRepository> {

	@Autowired
	private CoordenadoraRepository repository;

	@Autowired
	private PapelRepository papelRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPassowrdEncoder;

	public Coordenadora create(Coordenadora e, String papel) throws HibernateException {
		e.setPassword(bCryptPassowrdEncoder.encode(e.getPassword()));
		e.setPapel(papelRepository.findByName(papel).get());
		return repository.save(e);
	}

	public Optional<Coordenadora> findByUsername(String username) {
		return repository.findByUsername(username);
	}
}
