package com.ifam.sistema_estagio.model.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "coordenadora")
public class Coordenadora extends Usuario{
	
	@OneToMany(mappedBy = "coordenadora")
	private List<Banca> bancas;

	public List<Banca> getBancas() {
		return bancas;
	}

	public void setBancas(List<Banca> bancas) {
		this.bancas = bancas;
	}
}
