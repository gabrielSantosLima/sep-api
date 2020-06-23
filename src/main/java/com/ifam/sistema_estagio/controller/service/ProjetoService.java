package com.ifam.sistema_estagio.controller.service;

import org.springframework.stereotype.Service;

import com.ifam.sistema_estagio.model.entity.EstagioPCCT;
import com.ifam.sistema_estagio.model.entity.Projeto;

@Service
public class ProjetoService extends EstagioPcctService {
	
	public Projeto saveAsEstagio(EstagioPCCT e) {
		EstagioPCCT newE;

		try {
			newE = this.create((Projeto) e);

			return (Projeto) newE;
		} catch (Exception error) {
			error.printStackTrace();
			return null;
		}

	}
}
