package com.ifam.sistema_estagio.controller.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifam.sistema_estagio.model.entity.Banca;
import com.ifam.sistema_estagio.model.entity.EstagioPCCT;
import com.ifam.sistema_estagio.model.repository.BancaRepository;

@Service
public class BancaService extends GenericService<Banca, BancaRepository> {

	@Autowired
	private BancaRepository repository;

	public List<Banca> findByEstagioPcct(EstagioPCCT estagioPcct) {
		return repository.findByEstagioPcct(estagioPcct);
	}
}
