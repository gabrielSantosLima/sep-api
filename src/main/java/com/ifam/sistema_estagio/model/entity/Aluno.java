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
import com.ifam.sistema_estagio.util.enums.ModalidadeCurso;

@Entity
@Table(name = "aluno")
public class Aluno extends Usuario{
	
	@Column(nullable = false, name = "data_conclusao")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataConclusao;
	
	@Column(nullable = false,name = "curso")
	@Enumerated(EnumType.STRING)
	private Curso curso;
	
	@Column(nullable = false, name = "modalidade_curso")
	@Enumerated(EnumType.ORDINAL)
	private ModalidadeCurso modalidadeCurso;
	
	//Estagio ou Projeto
	@ManyToOne
	@JoinColumn(name = "estagio_pcct_id", nullable = false)
	private EstagioPCCT estagioPCCT;
	
	public EstagioPCCT getEstagioPCCT() {
		return estagioPCCT;
	}
	public void setEstagioPCCT(EstagioPCCT estagioPCCT) {
		this.estagioPCCT = estagioPCCT;
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
