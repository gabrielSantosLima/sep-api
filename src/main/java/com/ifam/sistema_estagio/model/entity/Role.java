package com.ifam.sistema_estagio.model.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "role")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@ManyToMany(mappedBy = "roles")
	private Set<Professor> professores;
	
	@ManyToMany(mappedBy = "roles")
	private Set<Coordenadora> coordenadoras;

	public Set<Professor> getProfessores() {
		return professores;
	}

	public void setProfessores(Set<Professor> professores) {
		this.professores = professores;
	}

	public Set<Coordenadora> getCoordenadoras() {
		return coordenadoras;
	}

	public void setCoordenadoras(Set<Coordenadora> coordenadoras) {
		this.coordenadoras = coordenadoras;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
