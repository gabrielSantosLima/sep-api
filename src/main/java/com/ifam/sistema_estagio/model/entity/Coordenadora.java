package com.ifam.sistema_estagio.model.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "coordenadora")
public class Coordenadora extends Usuario{
	
	//Bancas
	@OneToMany(mappedBy = "coordenadora")
	private List<Banca> bancas;
}
