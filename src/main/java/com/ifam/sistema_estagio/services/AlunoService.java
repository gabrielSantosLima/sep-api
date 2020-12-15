package com.ifam.sistema_estagio.services;

import java.util.List;
import java.util.Optional;

import com.ifam.sistema_estagio.entity.Coordenadora;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifam.sistema_estagio.entity.Aluno;
import com.ifam.sistema_estagio.repository.AlunoRepository;

@Service
public class AlunoService extends GenericService<Aluno, AlunoRepository>{

	@Autowired
	private AlunoRepository alunoRepository;

	@Autowired
	private EstagioPcctService estagioPcctService;

	public List<Aluno> encontrarPorNome(String nome){
		return alunoRepository.findByNomeContainingIgnoreCase(nome);
	}

	public List<Aluno> encontrarPorMatricula(String matricula){
		return alunoRepository.findByMatriculaContainingIgnoreCase(matricula);
	}

	public List<Aluno> encontrarPorEstagioPcct(String idEstagio) throws Exception {
		val estagioPCCT = estagioPcctService.encontrarPorId(idEstagio);
		val estagioNaoEncontrado = !estagioPCCT.isPresent();
		if(estagioNaoEncontrado) throw new Exception("Estágio/PCCT não existe");
		return alunoRepository.findByEstagioPcct(estagioPCCT.get());
	}

	@Override
	public Aluno salvar(Aluno aluno) throws Exception {
		val alunos = encontrarPorCpf(aluno.getCpf());
		if(alunos.size() > 0){
			return alunos.get(0);
		}
		return super.salvar(aluno);
	}


	public List<Aluno> encontrarPorCpf(String cpf){
		return alunoRepository.findByCpf(cpf);
	}
}
