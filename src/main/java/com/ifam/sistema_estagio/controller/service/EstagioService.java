package com.ifam.sistema_estagio.controller.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ifam.sistema_estagio.model.entity.Aluno;
import com.ifam.sistema_estagio.model.entity.Estagio;
import com.ifam.sistema_estagio.model.entity.EstagioPCCT;

@Service
public class EstagioService extends EstagioPcctService {

	@Autowired
	private AlunoService alunoService;

	@Transactional
	public Estagio saveAsEstagio(EstagioPCCT e, List<Aluno> alunos) {
		EstagioPCCT newE;

		alunos.forEach(aluno -> {
			aluno.setEstagioPcct(e);
			try {
				alunoService.create(aluno);
			} catch (Exception error) {
				error.printStackTrace();
			}
		});

		e.setAlunos(alunos);

		try {
			newE = this.create((Estagio) e);

			return (Estagio) newE;
		} catch (Exception error) {
			error.printStackTrace();
			return null;
		}
	}

	public List<Estagio> listEstagios() {
		List<Estagio> estagios = new ArrayList<>();

		for (EstagioPCCT estagio : list()) {
			if (estagio instanceof Estagio) {
				estagios.add((Estagio) estagio);
			}
		}

		return estagios;
	}

	@Transactional
	public EstagioPCCT updateEstagio(Integer id, EstagioPCCT estagioPcct) throws Exception {
		EstagioPCCT estagio = this.findById(id).get();

		List<Aluno> alunos = alunoService.findByEstagioPcct(estagio);

		EstagioPCCT newEstagio = update(id, estagioPcct);

		//Atualiza Alunos que estão cadastrados neste estágio
		alunos.forEach(aluno -> {
			aluno.setEstagioPcct(newEstagio);

			try {
				alunoService.update(aluno.getId(), aluno);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		return newEstagio;
	}

}
