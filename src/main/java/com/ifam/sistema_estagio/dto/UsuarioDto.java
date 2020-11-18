package com.ifam.sistema_estagio.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ifam.sistema_estagio.entity.Aluno;
import com.ifam.sistema_estagio.entity.Coordenadora;
import com.ifam.sistema_estagio.entity.Professor;
import com.ifam.sistema_estagio.util.enums.Curso;
import com.ifam.sistema_estagio.util.enums.FuncaoEstagio;
import com.ifam.sistema_estagio.util.enums.GrauAcademico;

import com.ifam.sistema_estagio.util.enums.ModalidadeCurso;
import lombok.*;
import org.springframework.boot.jackson.JsonComponent;

import java.io.File;
import java.io.Serializable;
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
	private File anexo;
	private String turma;
	private ModalidadeCurso modalidadeCurso;
	private Date anoFinalizacao;
	private FuncaoEstagio funcao;
	private GrauAcademico grau;
	private EstagioPCCTDto estagioPcct;

	@Override
	public Aluno construirAluno() {
		Aluno aluno = Aluno.builder()
				.anexo(anexo)
				.curso(curso)
				.dataConclusao(anoFinalizacao)
				.estagioPcct(estagioPcct.construirEntidade())
				.modalidadeCurso(modalidadeCurso)
				.turma(turma)
				.build();

		aluno.setCpf(cpf);
		aluno.setNomeCompleto(nome);
		aluno.setMatricula(matricula);
		aluno.setEmail(email);
		aluno.setTipo(funcao);
		aluno.setGrau(grau);

		return aluno;
	}

	@Override
	public Professor construirProfessor() {
		Professor professor = Professor.builder()
				.build();

		professor.setCpf(cpf);
		professor.setNomeCompleto(nome);
		professor.setMatricula(matricula);
		professor.setEmail(email);
		professor.setTipo(funcao);
		professor.setGrau(grau);

		return professor;
	}

	@Override
	public Coordenadora construirCoordenadora() {
		Coordenadora coordenadora = Coordenadora.builder()
				.build();

		coordenadora.setCpf(cpf);
		coordenadora.setNomeCompleto(nome);
		coordenadora.setMatricula(matricula);
		coordenadora.setEmail(email);
		coordenadora.setTipo(funcao);
		coordenadora.setGrau(grau);

		return coordenadora;
	}
}
