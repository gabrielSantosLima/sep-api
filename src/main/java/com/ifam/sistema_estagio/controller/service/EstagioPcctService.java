package com.ifam.sistema_estagio.controller.service;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ifam.sistema_estagio.model.entity.EstagioPCCT;
import com.ifam.sistema_estagio.model.repository.EstagioPcctRepository;
import com.ifam.sistema_estagio.util.enums.TipoServico;

@Service
public class EstagioPcctService extends GenericService<EstagioPCCT, EstagioPcctRepository>{
	
	public List<EstagioPCCT> getEstagiosOrProjeto(TipoServico tipo){
		List<EstagioPCCT> estagiosPcct = this.list();
		
		List<EstagioPCCT> result = estagiosPcct
				.stream()
				.filter(estagioPcct -> estagioPcct.getTipo() == tipo)
				.collect(Collectors.toList());
		
		return result;
	}
}
