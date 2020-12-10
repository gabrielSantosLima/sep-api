package com.ifam.sistema_estagio.services;

import com.ifam.sistema_estagio.util.enums.TipoServico;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifam.sistema_estagio.entity.EstagioPCCT;
import com.ifam.sistema_estagio.repository.EstagioPcctRepository;

import java.util.List;

@Service
public class EstagioPcctService extends GenericService<EstagioPCCT, EstagioPcctRepository>{
	
	@Autowired
	private EstagioPcctRepository estagioPcctRepository;

	public List<EstagioPCCT> listar(TipoServico tipo, String titulo) {
		if(tipo == null) return listar();
		if(titulo != null) return estagioPcctRepository.findByTitulo(titulo);
		return estagioPcctRepository.findByTipo(tipo);
	}

	public EstagioPCCT finalizarEstagioPcct(String idEstagio) throws Exception {
		val estagioPcct = encontrarPorId(idEstagio);
		val estagioPcctNaoExiste = !estagioPcct.isPresent();
		if(estagioPcctNaoExiste) throw new Exception("Estágio não existe");
		if(estagioPcct.get().getConcluido()) return estagioPcct.get();
		estagioPcct.get().setConcluido(true);
		return atualizar(idEstagio, estagioPcct.get());
	}
}
