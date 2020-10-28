package com.ifam.sistema_estagio.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifam.sistema_estagio.entity.Avaliadores;
import com.ifam.sistema_estagio.entity.Banca;
import com.ifam.sistema_estagio.entity.Coordenadora;
import com.ifam.sistema_estagio.entity.EstagioPCCT;
import com.ifam.sistema_estagio.entity.Professor;
import com.ifam.sistema_estagio.repository.BancaRepository;

@Service
public class BancaService extends GenericService<Banca, BancaRepository> {

	@Autowired
	private BancaRepository repository;

	@Autowired
	private AvaliadoresService avaliadoresService;

	@Autowired
	private ProfessorService professorService;

	@Autowired
	private CoordenadoraService coordenadoraService;

	public List<Banca> findByEstagioPcct(EstagioPCCT estagioPcct) {
		return repository.findByEstagioPcct(estagioPcct);
	}

	@Transactional
	public Banca create(Banca banca, EstagioPCCT estagioPcct) throws Exception {
		banca.setEstagioPcct(estagioPcct);
		Optional<Coordenadora> coordenadora = coordenadoraService
				.findById(banca.getCoordenadora().getId());

		if (!coordenadora.isPresent()) {
			throw new Exception("[banca-service] Coordenadora não existe!");
		}

		banca.setCoordenadora(coordenadora.get());

		for (Avaliadores avaliador : banca.getAvaliadores()) {
			Optional<Professor> professor = professorService
					.findById(avaliador.getProfessor().getId());

			if (!professor.isPresent()) {
				throw new Exception("[banca-service] Professor " + professor.get().getId()
						+ " não existe!");
			}

			avaliador.setBanca(banca);
			avaliador.setProfessor(professor.get());

			avaliadoresService.create(avaliador);
		}

		Banca createdBanca = this.create(banca);
		
		return createdBanca;
	}
}
