package com.ifam.sistema_estagio.dto;

import com.ifam.sistema_estagio.entity.Aluno;
import com.ifam.sistema_estagio.entity.Coordenadora;
import com.ifam.sistema_estagio.entity.Professor;
import com.ifam.sistema_estagio.util.enums.Curso;
import com.ifam.sistema_estagio.util.enums.FuncaoEstagio;
import com.ifam.sistema_estagio.util.enums.GrauAcademico;

import com.ifam.sistema_estagio.util.enums.ModalidadeCurso;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class UsuarioDto implements IObjetoUsuarioDto {
	private String id;
	private String matricula;
	private String cpf;
	private String nome;
	private String email;
	private Curso curso;
	private String turma;
	private ModalidadeCurso modalidadeCurso;
	private Date dataConclusao;
	private FuncaoEstagio funcao;
	private GrauAcademico grau;
	private EstagioPCCTDto estagioPcct;

	@Override
	public Aluno construirAluno() {
		Aluno aluno = Aluno.builder()
				.curso(curso)
				.dataConclusao(dataConclusao)
				.estagioPcct(estagioPcct.construirEntidade())
				.modalidadeCurso(modalidadeCurso)
				.turma(turma)
				.build();
		aluno.setId(id);
		aluno.setCpf(cpf);
		aluno.setNome(nome);
		aluno.setMatricula(matricula);
		aluno.setEmail(email);
		aluno.setTipo(funcao);
		aluno.setGrau(grau);
		aluno.setCurso(curso);
		return aluno;
	}

	@Override
	public Professor construirProfessor() {
		Professor professor = Professor.builder().build();
		professor.setId(id);
		professor.setCpf(cpf);
		professor.setNome(nome);
		professor.setMatricula(matricula);
		professor.setEmail(email);
		professor.setTipo(funcao);
		professor.setGrau(grau);
		return professor;
	}

	@Override
	public Coordenadora construirCoordenadora() {
		Coordenadora coordenadora = Coordenadora.builder().build();
		coordenadora.setId(id);
		coordenadora.setCpf(cpf);
		coordenadora.setNome(nome);
		coordenadora.setMatricula(matricula);
		coordenadora.setEmail(email);
		coordenadora.setTipo(funcao);
		coordenadora.setGrau(grau);
		return coordenadora;
	}
}
