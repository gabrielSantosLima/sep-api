package com.ifam.sistema_estagio.model.entity;

import java.sql.Time;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
	name = "tipo_servico",
	discriminatorType = DiscriminatorType.STRING,
	length = 1)
@Table(name = "estagio_pcct")
public class EstagioPCCT {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false, name = "titulo")
	private String titulo;

	@Column(nullable = false, name = "carga_horario")
	private Time cargaHoraria;
	
	//Bancas
	@OneToMany(mappedBy = "estagioPcct")
	private List<Banca> bancas;

	//Alunos
	@OneToMany(mappedBy = "estagioPcct")
	private List<Aluno> alunos;
	
	//Reponsável
	@ManyToOne
	@JoinColumn(name = "responsavel_id")
	private Professor responsavel;
	
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