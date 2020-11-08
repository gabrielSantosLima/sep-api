package com.ifam.sistema_estagio.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.ifam.sistema_estagio.dto.BancaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifam.sistema_estagio.entity.Avaliadores;
import com.ifam.sistema_estagio.entity.Banca;
import com.ifam.sistema_estagio.entity.Coordenadora;
import com.ifam.sistema_estagio.entity.EstagioPCCT;
import com.ifam.sistema_estagio.entity.Professor;
import com.ifam.sistema_estagio.repository.BancaRepository;

@Service
public class BancaService extends GenericService<Banca, BancaRepository> {

	@Autowired
	private BancaRepository repository;

	public List<Banca> findByEstagioPcct(EstagioPCCT estagioPcct) {
		return repository.findByEstagioPcct(estagioPcct);
	}

	@Transactional
	public Banca create(BancaDto banca){
		return new Banca();
	}
}
