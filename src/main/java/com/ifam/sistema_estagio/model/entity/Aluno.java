package com.ifam.sistema_estagio.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.ifam.sistema_estagio.util.enums.Curso;
import com.ifam.sistema_estagio.util.enums.FuncaoEstagio;
import com.ifam.sistema_estagio.util.enums.GrauAcademico;
import com.ifam.sistema_estagio.util.enums.ModalidadeCurso;

@Entity
@Table(name = "aluno")
public class Aluno extends Usuario{

	@Column(nullable = false, name = "data_conclusao")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataConclusao;

	@Column(nullable = false, name = "curso")
	@Enumerated(EnumType.STRING)
	private Curso curso;

	@Column(nullable = false, name = "modalidade_curso")
	@Enumerated(EnumType.STRING)
	private ModalidadeCurso modalidadeCurso;

	//Estágio ou projeto
	@ManyToOne
	@JoinColumn(name = "estagioPcct_id")
	private EstagioPCCT estagioPcct;

	public Aluno() {
		super();
	}
	
	public Aluno(Integer id, String matricula, String nome, String email, FuncaoEstagio tipo, Date dataConclusao, Curso curso,
			ModalidadeCurso modalidadeCurso, EstagioPCCT estagioPcct, GrauAcademico grau) {
		super(id, matricula, nome, email, tipo, grau);
		this.dataConclusao = dataConclusao;
		this.curso = curso;
		this.modalidadeCurso = modalidadeCurso;
		this.estagioPcct = estagioPcct;
	}

	public EstagioPCCT getEstagioPcct() {
		return estagioPcct;
	}

	public void setEstagioPcct(EstagioPCCT estagioPcct) {
		this.estagioPcct = estagioPcct;
	}

	public Date getDataConclusao() {
		return this.dataConclusao;
	}

	public void setDataConclusao(Date dataConclusao) {
		this.dataConclusao = dataConclusao;
	}

	public Curso getCurso() {
		return this.curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public ModalidadeCurso getModalidadeCurso() {
		return this.modalidadeCurso;
	}

	public void setModalidadeCurso(ModalidadeCurso modalidadeCurso) {
		this.modalidadeCurso = modalidadeCurso;
	}
}
