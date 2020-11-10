package com.ifam.sistema_estagio.services;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ifam.sistema_estagio.entity.EstagioPCCT;
import com.ifam.sistema_estagio.repository.EstagioPcctRepository;
import com.ifam.sistema_estagio.util.enums.TipoServico;

@Service
public class EstagioPcctService extends GenericService<EstagioPCCT, EstagioPcctRepository>{
	
	public List<EstagioPCCT> getEstagiosOrProjeto(TipoServico tipo){
		List<EstagioPCCT> estagiosPcct = this.listar();
		
		List<EstagioPCCT> result = estagiosPcct
				.stream()
				.filter(estagioPcct -> estagioPcct.getTipo() == tipo)
				.collect(Collectors.toList());
		
		return result;
	}
}
