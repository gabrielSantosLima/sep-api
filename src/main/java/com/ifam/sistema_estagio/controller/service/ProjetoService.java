package com.ifam.sistema_estagio.controller.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ifam.sistema_estagio.model.entity.Aluno;
import com.ifam.sistema_estagio.model.entity.EstagioPCCT;
import com.ifam.sistema_estagio.model.entity.Projeto;

@Service
public class ProjetoService extends EstagioPcctService {
	
	@Autowired
	private AlunoService alunoService;

	@Transactional
	public Projeto saveAsProjeto(EstagioPCCT e, List<Aluno> alunos) {
		EstagioPCCT newP;

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
			newP = this.create((Projeto) e);

			return (Projeto) newP;
		} catch (Exception error) {
			error.printStackTrace();
			return null;
		}
	}

	public List<Projeto> listProjetos() {
		List<Projeto> projetos = new ArrayList<>();

		for (EstagioPCCT projeto : list()) {
			if (projeto instanceof Projeto) {
				projetos.add((Projeto) projeto);
			}
		}

		return projetos;
	}

	@Transactional
	public EstagioPCCT updateProjeto(Integer id, EstagioPCCT estagioPcct) throws Exception {
		EstagioPCCT projeto = this.findById(id).get();

		List<Aluno> alunos = alunoService.findByEstagioPcct(projeto);

		EstagioPCCT newProjeto = update(id, estagioPcct);

		//Atualiza Alunos que estão cadastrados neste estágio
		alunos.forEach(aluno -> {
			aluno.setEstagioPcct(newProjeto);

			try {
				alunoService.update(aluno.getId(), aluno);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		return newProjeto;
	}
}
