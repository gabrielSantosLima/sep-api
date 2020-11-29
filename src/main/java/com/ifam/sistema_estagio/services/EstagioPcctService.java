package com.ifam.sistema_estagio.services;

import com.ifam.sistema_estagio.util.enums.Curso;
import com.ifam.sistema_estagio.util.enums.TipoServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifam.sistema_estagio.entity.EstagioPCCT;
import com.ifam.sistema_estagio.repository.EstagioPcctRepository;

import java.util.List;

@Service
public class EstagioPcctService extends GenericService<EstagioPCCT, EstagioPcctRepository>{
	
	@Autowired
	private EstagioPcctRepository estagioPcctRepository;

	public List<EstagioPCCT> listarPorCursoETipo(Curso curso, TipoServico tipo) throws Exception {
		if(tipo == null) return listar();
		if(curso == null) return estagioPcctRepository.findByTipo(tipo);
		return estagioPcctRepository.findByCursoAndTipo(curso,tipo);
	}
}
