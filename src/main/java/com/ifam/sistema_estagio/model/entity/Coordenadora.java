package com.ifam.sistema_estagio.model.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.ifam.sistema_estagio.model.entity.interfaces.UsuarioLogavel;

@Entity
@Table(name = "coordenadora")
public class Coordenadora extends Usuario implements UsuarioLogavel{
	
	//Bancas
	@OneToMany(mappedBy = "coordenadora")
	private List<Banca> bancas;
	
	@ManyToMany
	private Set<Role> roles;

	public Coordenadora() {
		
	}
	
	public Coordenadora(Integer id, String matricula, String nome, String email, String username, String password,
			String passwordConfirm, List<Banca> bancas) {
		super(id, matricula, nome, email, username, password, passwordConfirm);
		this.bancas = bancas;
	}

	public List<Banca> getBancas() {
		return bancas;
	}

	public void setBancas(List<Banca> bancas) {
		this.bancas = bancas;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
}
