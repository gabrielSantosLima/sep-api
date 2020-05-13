package com.ifam.sistema_estagio.model.entity;

import java.util.List;

import javax.persistence.Column;
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

	//Preenche ficha de avaliação
	@OneToMany(mappedBy = "professor")
	private List<FichaDeAvaliacao> fichaAvaliacao; 
	
	//Participa da banca de avaliação
	@ManyToMany(mappedBy = "professores")
	private List<Banca> bancas;
	
	//Supervisiona ou não um ou mais estágios ou projetos
	@OneToMany(mappedBy = "responsavel")
	private List<EstagioPCCT> estagioPcct;
	
	public List<EstagioPCCT> getEstagioPcct() {
		return estagioPcct;
	}

	public List<FichaDeAvaliacao> getFichaAvaliacao() {
		return fichaAvaliacao;
	}

	public void setFichaAvaliacao(List<FichaDeAvaliacao> fichaAvaliacao) {
		this.fichaAvaliacao = fichaAvaliacao;
	}

	public List<Banca> getBancas() {
		return bancas;
	}

	public void setBancas(List<Banca> bancas) {
		this.bancas = bancas;
	}

	public void setEstagioPcct(List<EstagioPCCT> estagioPcct) {
		this.estagioPcct = estagioPcct;
	}

	public GrauAcademico getGrau() {
		return grau;
	}

	public void setGrau(GrauAcademico grau) {
		this.grau = grau;
	}
}
