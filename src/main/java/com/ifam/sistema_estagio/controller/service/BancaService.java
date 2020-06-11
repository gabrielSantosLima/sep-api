package com.ifam.sistema_estagio.controller.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifam.sistema_estagio.model.entity.Banca;
import com.ifam.sistema_estagio.model.entity.Estagio;
import com.ifam.sistema_estagio.model.entity.EstagioPCCT;
import com.ifam.sistema_estagio.model.entity.Projeto;
import com.ifam.sistema_estagio.model.repository.BancaRepository;
import com.ifam.sistema_estagio.util.enums.TipoServico;

@Service
public class BancaService extends GenericService<Banca, BancaRepository> {

	@Autowired
	private BancaRepository repository;

	public List<Banca> findByEstagioPcct(EstagioPCCT estagioPcct) {
		return repository.findByEstagioPcct(estagioPcct);
	}

	public Banca setEstagioOrProjeto(Banca banca, EstagioPCCT estagioPcct) throws Exception {
		if (estagioPcct instanceof Projeto) {
			banca.setTipo(TipoServico.PROJETO);

			return banca;
		} else if(estagioPcct instanceof Estagio) {
			banca.setTipo(TipoServico.ESTAGIO);

			return banca;
		}else {
			throw new Exception("[service-banca] Objeto banca não possui instância em estágio ou projeto");
		}

	}
}
