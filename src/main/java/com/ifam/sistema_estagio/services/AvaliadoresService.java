package com.ifam.sistema_estagio.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifam.sistema_estagio.entity.Avaliadores;
import com.ifam.sistema_estagio.entity.Banca;
import com.ifam.sistema_estagio.entity.Professor;
import com.ifam.sistema_estagio.repository.AvaliadoresRepository;

@Service
public class AvaliadoresService extends GenericService<Avaliadores, AvaliadoresRepository>{
	
	@Autowired
	private AvaliadoresRepository avaliadoresRepository;
	
	@Autowired 
	private BancaService bancaService;

	@Autowired 
	private ProfessorService professorService;
	
	private Optional<Banca> getBancaById(Integer id) {
		return bancaService.encontrarPorId(id);
	}
	
	public List<Avaliadores> findByBancaId(Integer idBanca) throws Exception{
		Optional<Banca> banca = getBancaById(idBanca);
		
		if(!banca.isPresent()) {
			throw new Exception("[avaliadores-service] Id inválido");
		}
		
		return avaliadoresRepository.findByBanca(banca.get());			
	}
	
	public Avaliadores createByIds(Integer idProfessor, Integer idBanca) throws Exception {
		Optional<Banca> banca = getBancaById(idBanca);
		Optional<Professor> professor = professorService.encontrarPorId(idProfessor);
		
		if(!banca.isPresent() || !professor.isPresent()) {
			throw new Exception("[avaliadores-service] Id inválido");
		}
		
		Avaliadores createdAvaliador = this.salvar(new Avaliadores(
				professor.get(), 
				banca.get())
		);
		
		return createdAvaliador;
	}
}
