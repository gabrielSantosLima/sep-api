package com.ifam.sistema_estagio.services;

import java.util.Date;
import java.util.List;

import com.ifam.sistema_estagio.util.enums.Curso;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifam.sistema_estagio.entity.Banca;
import com.ifam.sistema_estagio.repository.BancaRepository;

@Service
public class BancaService extends GenericService<Banca, BancaRepository> {

	@Autowired
	private BancaRepository bancaRepository;

	public Boolean finalizarBanca(String idBanca) throws Exception {
		val banca = encontrarPorId(idBanca);
		val naoExiste = !banca.isPresent();
		if(naoExiste) return false;
		banca.get().setHoraFinalizado(new Date());
		atualizar(idBanca, banca.get());
		return true;
	}

	public List<Banca> listarPorCurso(Curso curso){
		if(curso == null) return listar();
		return bancaRepository.findByCurso(curso);
	}
}
