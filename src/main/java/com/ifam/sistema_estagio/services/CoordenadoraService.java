package com.ifam.sistema_estagio.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifam.sistema_estagio.entity.Coordenadora;
import com.ifam.sistema_estagio.repository.CoordenadoraRepository;

@Service
public class CoordenadoraService extends GenericService<Coordenadora, CoordenadoraRepository> {

	@Autowired
	private CoordenadoraRepository repository;

	public Optional<Coordenadora> findByNomeCompleto(String nomeCompleto) {
		return repository.findByNomeCompleto(nomeCompleto);
	}
}
