package com.ifam.sistema_estagio.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.ifam.sistema_estagio.util.enums.Curso;
import com.ifam.sistema_estagio.util.enums.ModalidadeCurso;

@Entity
@Table(name = "aluno")
public class Aluno {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false, name = "matricula")
	private String matricula;
	
	@Column(nullable = false, length = 200, name = "nome")
	private String nome;

	@Column(nullable = false, length = 100, name = "email")
	private String email;
	
	@Column(nullable = false, name = "data_conclusao")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataConclusao;

	@Column(nullable = false, name = "curso")
	@Enumerated(EnumType.STRING)
	private Curso curso;

	@Column(nullable = false, name = "modalidade_curso")
	@Enumerated(EnumType.ORDINAL)
	private ModalidadeCurso modalidadeCurso;

	//Estágio ou projeto
	@ManyToOne
	@JoinColumn(name = "estagioPcct_id")
	private EstagioPCCT estagioPcct;

	public Aluno() {
		
	}
	
	public Aluno(Integer id, String matricula, String nome, String email, Date dataConclusao, Curso curso,
			ModalidadeCurso modalidadeCurso, EstagioPCCT estagioPcct) {
		this.id = id;
		this.matricula = matricula;
		this.nome = nome;
		this.email = email;
		this.dataConclusao = dataConclusao;
		this.curso = curso;
		this.modalidadeCurso = modalidadeCurso;
		this.estagioPcct = estagioPcct;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
