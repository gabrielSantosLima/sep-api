package com.ifam.sistema_estagio.services;

import java.util.List;
import java.util.Optional;

import com.ifam.sistema_estagio.entity.Professor;
import lombok.val;
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

	public List<Coordenadora> encontrarPorMatricula(String matricula){
		return coordenadoraRepository.findByMatriculaContainingIgnoreCase(matricula);
	}

	@Override
	public Coordenadora salvar(Coordenadora coordenadora) throws Exception {
		val coordenadoraOptional = encontrarPorCpf(coordenadora.getCpf());
		if(coordenadoraOptional.size() > 0){
			return coordenadoraOptional.get(0);
		}
		return super.salvar(coordenadora);
	}

	public List<Coordenadora> encontrarPorCpf(String cpf){
		return coordenadoraRepository.findByCpf(cpf);
	}
}
