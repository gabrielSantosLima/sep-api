package com.ifam.sistema_estagio.model.entity;

import javax.persistence.Column;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.ifam.sistema_estagio.util.enums.GrauAcademico;

@Entity
@Table(name = "professor")
public class Professor extends Usuario {

	@Column(nullable = false, name = "grau")
	@Enumerated(EnumType.STRING)
	private GrauAcademico grau;

	@ManyToMany(mappedBy = "professores")
	private List<Banca> bancas;
	
	//Fichas de avaliação de estágios
	@OneToMany(mappedBy = "professor")
	private List<FichaDeAvaliacaoEstagio> fichaEstagios;
	
	//Fichas de avaliação de projetos
	@OneToMany(mappedBy = "professor")
	private List<FichaDeAvaliacaoProjeto> fichaProjeto;
	
	//Projetos ou estágios
	@OneToMany(mappedBy = "responsavel")
	private List<EstagioPCCT> estagiosPcct;
	
	public GrauAcademico getGrau() {
		return grau;
	}

	public void setGrau(GrauAcademico grau) {
		this.grau = grau;
	}
}
