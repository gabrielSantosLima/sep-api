package com.ifam.sistema_estagio.model.entity;

import java.sql.Time;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class EstagioPCCT {
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Integer id;
	
	@Column(nullable = false, name = "titulo")
	private String titulo;

	@Column(nullable = false, name = "carga_horario")
	private Time cargaHoraria;

	//Um ou mais alunos participantes
	@OneToMany(mappedBy = "estagioPCCT")
	private List<Aluno> alunos;
	
	// Chefe ou orientador responsável
	@ManyToOne
	@JoinColumn(name = "professor_id", nullable = false)
	private Professor responsavel;
	
	//Bancas cadastradas
	@OneToMany(mappedBy = "estagioPcct")
	private List<Banca> bancas;
	
	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

	public Professor getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Professor responsavel) {
		this.responsavel = responsavel;
	}

	public List<Banca> getBancas() {
		return bancas;
	}

	public void setBancas(List<Banca> bancas) {
		this.bancas = bancas;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Time getCargaHoraria() {
		return cargaHoraria;
	}

	public void setCargaHoraria(Time cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}
}
