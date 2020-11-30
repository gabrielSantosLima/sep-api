package com.ifam.sistema_estagio.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifam.sistema_estagio.entity.Coordenadora;
import com.ifam.sistema_estagio.repository.CoordenadoraRepository;

@Service
public class CoordenadoraService extends GenericService<Coordenadora, CoordenadoraRepository> {

	@Autowired
	private CoordenadoraRepository coordenadoraRepository;

	public List<Coordenadora> encontrarPorNome(String nome){
		return coordenadoraRepository.findByNomeContainingIgnoreCase(nome);
	}
}
